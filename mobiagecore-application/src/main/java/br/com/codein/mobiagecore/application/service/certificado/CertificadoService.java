package br.com.codein.movementgroup.application.service.invoice;

import br.com.codein.mobiagecore.domain.utils.FileUtils;
import br.com.codein.movementgroup.application.repository.invoice.CertificadoRepository;
import br.com.codein.movementgroup.domain.model.comission.Certificado;
import io.gumga.application.GumgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Security;

@Service
public class CertificadoService extends GumgaService<Certificado, Long> {

    private CertificadoRepository repository;

    @Autowired
    public CertificadoService(CertificadoRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public void setCertificado(Certificado certificado){

        InputStream iSCacert = this.getClass().getClassLoader().getResourceAsStream("cacert");;

        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");
        System.setProperty("jdk.tls.client.protocols", "TLSv1");

        String certificate = FileUtils.createTempFile(new ByteArrayInputStream(certificado.getBytes()),"");


        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.keyStore", certificate);


        System.setProperty("javax.net.ssl.keyStorePassword", certificado.getPassword());
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        String cacert = FileUtils.createTempFile(iSCacert,"cacert");



        System.setProperty("javax.net.ssl.trustStore", cacert);
    }

    public void cleanCertificado(){
        System.clearProperty("javax.net.ssl.keyStore");
        System.clearProperty("javax.net.ssl.keyStorePassword");
        System.clearProperty("javax.net.ssl.trustStore");

    }

}