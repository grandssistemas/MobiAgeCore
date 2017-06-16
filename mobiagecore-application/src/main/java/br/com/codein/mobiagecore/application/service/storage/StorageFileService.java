package br.com.codein.mobiagecore.application.service.storage;

import br.com.codein.mobiagecore.application.repository.storage.StorageFileRepository;
import br.com.codein.mobiagecore.domain.model.storage.StorageFile;
import br.com.codein.mobiagecore.domain.model.storage.enums.ImageType;
import br.com.codein.mobiagecore.integration.storage.StorageClient;
import io.gumga.application.GumgaService;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        save(sf);
        return sf;
    }

    @Transactional
    public StorageFile saveXml (File file, String key) throws IOException {
        Map map = storageClient.uploadFile(file, key).getBody();
        if (((ArrayList)map.get("arquivos")).size() > 0) {
            StorageFile sf = new StorageFile();
            sf.setUrl(String.valueOf(((Map) map.get("object")).get("localizacaoStorage")));
            sf.setIdStorage(Long.valueOf(String.valueOf(((Map) map.get("object")).get("id"))));
            save(sf);
            return sf;
        } else {
            return null;
        }
    }

    @Transactional
    public StorageFile saveSharedImage (File file, String key, Long integrationId) {
        Map map = storageClient.uploadSharedImage(file, key).getBody();
        StorageFile sf = new StorageFile();
        sf.setUrl(String.valueOf(((Map)map.get("object")).get("localizacaoStorage")));
        sf.setIdStorage(Long.valueOf(String.valueOf(((Map)map.get("object")).get("id"))));
        sf.setType(ImageType.PRIMARY);
        sf.setIntegrationId(integrationId);
        save(sf);
        return sf;
    }

    @Transactional(readOnly = true)
    public List<StorageFile> getStorageFileList() {
        QueryObject qo = new QueryObject();
        qo.setAq("obj.id > 0");
        qo.setPageSize(999999);
        return pesquisa(qo).getValues();
    }

    @Transactional(readOnly = true)
    public StorageFile getByIdStorage(Long idStorage) {
        QueryObject qo = new QueryObject();
        qo.setAq("obj.idStorage = " + idStorage);
        SearchResult<StorageFile> search = this.repository.search(qo);
        return search.getValues().size() > 0?(StorageFile) search.getValues().get(0):null;
    }
}
