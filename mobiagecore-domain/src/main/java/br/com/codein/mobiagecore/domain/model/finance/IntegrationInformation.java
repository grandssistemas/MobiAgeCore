package br.com.codein.mobiagecore.domain.model.finance;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafael on 04/08/17.
 */
@Embeddable
public class IntegrationInformation implements Serializable {
    private Boolean integrationValid;
    private String integrationInfo;
    private Date integrationDate;

    public IntegrationInformation() {
    }

    public IntegrationInformation(Boolean integrationValid) {
        this.integrationValid = integrationValid;
        this.integrationDate = new Date();
    }

    public IntegrationInformation(Boolean integrationValid, String integrationInfo) {
        this.integrationValid = integrationValid;
        this.integrationInfo = integrationInfo;
        this.integrationDate = new Date();
    }

    public IntegrationInformation(Boolean integrationValid, String integrationInfo, Date integrationDate) {
        this.integrationValid = integrationValid;
        this.integrationInfo = integrationInfo;
        this.integrationDate = integrationDate;
    }

    public Boolean getIntegrationValid() {
        return integrationValid;
    }

    public void setIntegrationValid(Boolean integrationValid) {
        this.integrationValid = integrationValid;
    }

    public Date getIntegrationDate() {
        return integrationDate;
    }

    public void setIntegrationDate(Date integrationDate) {
        this.integrationDate = integrationDate;
    }

    public String getIntegrationInfo() {
        return integrationInfo;
    }

    public void setIntegrationInfo(String integrationInfo) {
        this.integrationInfo = integrationInfo;
    }
}
