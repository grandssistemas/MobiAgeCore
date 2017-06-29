package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.grands.financeclient.modelo.titulo.UnidadeFinanceira;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceUnidadeFinanceiraClient extends AbstractClient<UnidadeFinanceira> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceUnidadeFinanceiraClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public List<UnidadeFinanceira> getUnidadeFinanceira(String name){
        return this.getList("/api/integration/getfinanceunitintegration?name=" + name).getBody();
    }
}
