package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.grands.financeclient.modelo.titulo.Titulo;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceTituloClient extends AbstractClient<Titulo> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceTituloClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public Titulo createTitulo(Titulo titulo){
        return this.post("/api/title/saveintegration", titulo).getBody();
    }
}
