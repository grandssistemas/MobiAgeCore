package br.com.codein.mobiagecore.domain.model.genericreport.enums;

/**
 * Created by gelatti on 29/06/17.
 */
public enum ReportType {

    PERSON("Pessoa"),
    PRODUCT("Produto"),
    SALE("Venda"),
    CONSIGMENT("Consignado"),
    FISCAL("Fiscal"),
    HEADERFOOTER("Cabeçalho e Rodapé");

    private final String label;

    ReportType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name();
    }
}
