package br.com.codein.mobiagecore.domain.model.finance;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by rafael on 04/08/17.
 */
@Embeddable
public class FinanceIntegration implements Serializable {
    private Boolean financeValid;
    private String integrationInfo;

    public FinanceIntegration() {
    }

    public FinanceIntegration(Boolean financeValid) {
        this.financeValid = financeValid;
    }

    public FinanceIntegration(Boolean financeValid, String integrationInfo) {
        this.financeValid = financeValid;
        this.integrationInfo = integrationInfo;
    }

    public Boolean getFinanceValid() {
        return financeValid;
    }

    public void setFinanceValid(Boolean financeValid) {
        this.financeValid = financeValid;
    }

    public String getIntegrationInfo() {
        return integrationInfo;
    }

    public void setIntegrationInfo(String integrationInfo) {
        this.integrationInfo = integrationInfo;
    }
}
