package br.com.codein.mobiagecore.domain.model.marking.enums;

public enum MarkingOrigin {
    PRODUCT("Produto"),PERSON("Pessoa"),OPERATION("Operação"),PAYMENT("Tipo de Pagamento"), MOVEMENTGROUP("Movimentação"), CLIENTGROUP("Grupo de Cliente");
    private final String value;

    MarkingOrigin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MarkingOrigin getByName(String name){
        switch (name){
            case "PRODUCT":
                return PRODUCT;
            case "PERSON":
                return PERSON;
            case "OPERATION":
                return OPERATION;
            case "PAYMENT":
                return PAYMENT;
            case "CLIENTGROUP":
                return CLIENTGROUP;
            default:
                return null;

        }
    }
}