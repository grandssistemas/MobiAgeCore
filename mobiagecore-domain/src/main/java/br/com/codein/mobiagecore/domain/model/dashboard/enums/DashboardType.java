package br.com.codein.mobiagecore.domain.model.dashboard.enums;

/**
 * Created by gelatti on 09/06/17.
 */
public enum DashboardType {

    SALE("Venda"),
    EXCHANGE("Troca"),
    CONSIGNMENT("Consignado"),
    CASHCHECKOUT("Fechamento de Caixa");

    private final String label;

    DashboardType (String label) {
        this.label = label;
    }

    public String getName() {
        return this.name();
    }

    public String getLabel() {
        return label;
    }
}
