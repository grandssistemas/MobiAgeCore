package br.com.codein.mobiagecore.integration.storage;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.Properties;

/**
 * Created by gelatti on 10/05/17.
 */
@Component
public class StorageClient extends AbstractClient<Map> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public StorageClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("storage.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public ResponseEntity<Map> uploadSharedImage(File file, String key) {
        String url = "/api/arquivos/upload/imagem-compartilhada/containers/" + key;
        return super.postFile(url, file);
    }

    public ResponseEntity<Map> uploadImage(File file, String key) {
        String url = "/api/arquivos/upload/imagem/containers/" + key;
        return super.post(url, file);
    }
}
