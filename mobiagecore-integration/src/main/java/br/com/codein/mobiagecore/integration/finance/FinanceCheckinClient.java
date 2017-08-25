package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.grands.financeclient.modelo.titulo.Abertura;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class FinanceCheckinClient extends AbstractClient<Abertura> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceCheckinClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }


    public Abertura createCheckin(Abertura abertura){
        return this.post("/api/integration/cashcheckin", abertura).getBody();
    }


    public Abertura updateCheckin(Long id, Abertura abertura){
        return this.put(String.format("/api/integration/cashcheckin/%d", id), abertura).getBody();
    }
}
