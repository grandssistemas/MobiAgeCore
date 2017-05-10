package br.com.codein.mobiagecore.domain.model.storage;

import br.com.codein.mobiagecore.domain.model.storage.enums.ImageType;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;

/**
 * Created by gelatti on 03/05/17.
 */
@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_STORAGE")
@Audited
@Entity
public class StorageFile extends GumgaModel<Long> {

    @ApiModelProperty(value = "Url do arquivo salvo no Storage", position = 1)
    private String url;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Atributo que diz se a imagem é primaria ou secundaria", position = 2)
    private ImageType type;
    @ApiModelProperty(value = "Armazena o ID da imagem no Storage")
    private Long idStorage;

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
}