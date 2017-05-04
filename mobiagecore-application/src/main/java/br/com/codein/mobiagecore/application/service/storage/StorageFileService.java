package br.com.codein.mobiagecore.application.service.storage;

import br.com.codein.mobiagecore.application.repository.storage.StorageFileRepository;
import br.com.codein.mobiagecore.domain.model.storage.StorageFile;
import io.gumga.application.GumgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gelatti on 03/05/17.
 */
@Component
public class StorageFileService extends GumgaService<StorageFile, Long> {

    private StorageFileRepository repository;

    @Autowired
    public StorageFileService(StorageFileRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
