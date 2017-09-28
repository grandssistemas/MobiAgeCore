package br.com.codein.mobiagecore.domain.model.genericreport;

import br.com.codein.mobiagecore.domain.model.genericreport.enums.ReportType;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;

/**
 * Created by gelatti on 29/06/17.
 */
@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_GENERICREPORT")
@Audited
@Entity
public class GenericReport extends GumgaModel<Long> {

    @ApiModelProperty(value = "JSON referente ao relatorio salvo")
    private String jsonReport;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Tipo do relatorio salvo")
    private ReportType reportType;
    @ApiModelProperty(value = "Nome do relatorio salvo")
    private String name;
    @ApiModelProperty(value = "Flag que indica se esse é o relatório padrão para esse tipo.")
    private Boolean isDefault = Boolean.FALSE;

    public GenericReport() {
    }

    public GenericReport(String name, ReportType reportType, String jsonReport) {
        this.jsonReport = jsonReport;
        this.reportType = reportType;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonReport() {
        return jsonReport;
    }

    public void setJsonReport(String jsonReport) {
        this.jsonReport = jsonReport;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}

