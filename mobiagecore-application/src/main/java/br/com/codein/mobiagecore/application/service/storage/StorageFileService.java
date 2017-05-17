package br.com.codein.mobiagecore.application.service.storage;

import br.com.codein.mobiagecore.application.repository.storage.StorageFileRepository;
import br.com.codein.mobiagecore.domain.model.storage.StorageFile;
import br.com.codein.mobiagecore.domain.model.storage.enums.ImageType;
import br.com.codein.mobiagecore.integration.storage.StorageClient;
import io.gumga.application.GumgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Map;

/**
 * Created by gelatti on 03/05/17.
 */
@Component
public class StorageFileService extends GumgaService<StorageFile, Long> {

    private StorageFileRepository repository;

    @Autowired
    private StorageClient storageClient;

    @Autowired
    public StorageFileService(StorageFileRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Transactional
    public StorageFile saveSharedImage (File file, String key) {
        Map map = storageClient.uploadSharedImage(file, key).getBody();
        StorageFile sf = new StorageFile();
        sf.setUrl(String.valueOf(((Map)map.get("object")).get("localizacaoStorage")));
        sf.setIdStorage(Long.valueOf(String.valueOf(((Map)map.get("object")).get("id"))));
        sf.setType(ImageType.PRIMARY);
        return super.save(sf);
    }

    @Transactional
    public StorageFile saveSharedImage (File file, String key, Long integrationId) {
        Map map = storageClient.uploadSharedImage(file, key).getBody();
        StorageFile sf = new StorageFile();
        sf.setUrl(String.valueOf(((Map)map.get("object")).get("localizacaoStorage")));
        sf.setIdStorage(Long.valueOf(String.valueOf(((Map)map.get("object")).get("id"))));
        sf.setIntegrationId(integrationId);
        sf.setType(ImageType.PRIMARY);
        return super.save(sf);
    }
}
