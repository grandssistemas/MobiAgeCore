package br.com.codein.mobiagecore.domain.model.storage;

import br.com.codein.mobiagecore.domain.model.storage.enums.ImageType;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by gelatti on 03/05/17.
 */
@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_STORAGE")
@Audited
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"oi", "integrationid"}, name = "integration_unique_storagefile"),
})
public class StorageFile extends GumgaModel<Long> {

    @ApiModelProperty(value = "Url do arquivo salvo no Storage", position = 1)
    private String url;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Atributo que diz se a imagem é primaria ou secundaria", position = 2)
    private ImageType type;
    @ApiModelProperty(value = "Armazena o ID da imagem no Storage", position = 3)
    private Long idStorage;
    @ApiModelProperty(value = "ID usado para integração com outros softwares", position = 4)
    private Long integrationId;

    public StorageFile() {
    }

    public StorageFile(String url, ImageType type, Long idStorage) {
        this.url = url;
        this.type = type;
        this.idStorage = idStorage;
    }
    public StorageFile(StorageFile file) {
        this.url = file.getUrl();
        this.type = file.getType();
        this.idStorage = file.getIdStorage();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Long getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(Long idStorage) {
        this.idStorage = idStorage;
    }

    public Long getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(Long integrationId) {
        this.integrationId = integrationId;
    }
}
