package br.com.codein.mobiagecore.domain.model.certificado;

import br.com.codein.mobiagecore.domain.exceptions.CertificateException;
import br.com.codein.mobiagecore.domain.utils.PasswordUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_CERTIFICADO")
@Audited
@Entity
public class Certificado extends GumgaModel<Long> {


    @Column(columnDefinition = "bytea")
    @ApiModelProperty("Bytes do arquivo do certificado digital")
    private byte[] bytes;

    @ApiModelProperty("Senha do certificado digital")
    private String password;
    @ApiModelProperty("Alias do certificado, gerado pela keystore")
    private String alias;

    public Certificado() {
    }


    public Certificado(byte[] bytes, String password) {
        this.bytes = bytes;
        try {
            this.password = PasswordUtils.encrypt(password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyStore ks = this.getKeyStore();
        if (ks == null) {
            throw new CertificateException("Impossible to create the keystore.");
        }
        try {
            this.alias = ks.aliases().nextElement();
        } catch (KeyStoreException e) {
            throw new CertificateException("Impossible to create alias from this keystore.");
        }

    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @JsonIgnore
    public KeyStore getKeyStore() {
        try {
            KeyStore ks;
            ks = KeyStore.getInstance("PKCS12");
            ks.load(new ByteArrayInputStream(this.bytes), PasswordUtils.decrypt(this.password.toCharArray()).toCharArray());
            return ks;
        } catch (Exception e) {
            return null;
        }

    }

    @JsonIgnore
    public X509Certificate getCertificate() {
        KeyStore ks = this.getKeyStore();
        if (ks == null) {
            return null;
        }
        try {
            return (X509Certificate) ks.getCertificate(this.alias);
        } catch (Exception e) {
            return null;
        }
    }

    @JsonIgnore
    public PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) this.getKeyStore().getKey(this.alias, PasswordUtils.decrypt(this.password.toCharArray()).toCharArray());
        } catch (Exception e) {
            return null;
        }
    }
}