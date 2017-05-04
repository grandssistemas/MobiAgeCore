package br.com.codein.mobiagecore.service;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.application.service.storage.StorageFileService;
import br.com.codein.mobiagecore.domain.model.storage.StorageFile;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Created by gelatti on 03/05/17.
 */
public class StorageFileServiceTest extends AbstractTest {

    @Autowired
    private StorageFileService storageFileService;

    @Test
    public void testGet() {
        SearchResult<StorageFile> storageFiles = storageFileService.pesquisa(new QueryObject());
        assertNotNull(storageFiles);
    }
}
