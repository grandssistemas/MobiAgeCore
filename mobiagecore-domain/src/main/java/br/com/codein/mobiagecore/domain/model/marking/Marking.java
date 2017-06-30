package br.com.codein.mobiagecore.domain.model.marking;

import br.com.codein.mobiagecore.domain.model.marking.enums.MarkingOrigin;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

/**
 * Created by nakamura on 31/05/17.
 */
@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_MARKING")
@Audited
@Entity
public class Marking extends GumgaModel<Long> implements Serializable{

    @ApiModelProperty(value = "Atributo utilizado para criar o nome do marcador")
    private String value;

    @Enumerated(EnumType.STRING)
    private MarkingOrigin origin;

    public Marking() {
    }

    public Marking(String value, MarkingOrigin origin) {
        this.value = value;
        this.origin = origin;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public MarkingOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(MarkingOrigin origin) {
        this.origin = origin;
    }
}
