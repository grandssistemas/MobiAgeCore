package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.grands.financeclient.modelo.titulo.Transferencia;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class FinanceTransferenciaClient extends AbstractClient<Transferencia> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceTransferenciaClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public List<Transferencia> createTransferencia(List<Transferencia> transferencias){
        return this.post("/api/transferintegration/saveintegration", transferencias).getBody();
    }
}
