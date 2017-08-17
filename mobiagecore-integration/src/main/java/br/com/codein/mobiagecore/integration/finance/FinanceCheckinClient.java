package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class FinanceCheckinClient extends AbstractClient<ObjectNode> {

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


    public ObjectNode createCheckin(ObjectNode node){
        return this.post("/api/checkin", node).getBody();
    }
}
