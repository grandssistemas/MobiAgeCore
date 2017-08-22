package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Properties;

@Component
public class FinanceGenericClient extends AbstractClient<Object> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceGenericClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if (properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public BigDecimal getFinanceUnitBalance(Long financeUnitId) {
        return new BigDecimal(this.get("/api/integration/financeunit/getbalance?financeUnitId=" + financeUnitId).getBody().toString());
    }
}
