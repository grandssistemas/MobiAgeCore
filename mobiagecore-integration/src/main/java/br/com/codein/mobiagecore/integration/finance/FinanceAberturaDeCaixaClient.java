package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.codein.mobiagecore.integration.generic.AbstractClientNoClass;
import br.com.grands.financeclient.modelo.titulo.Pessoa;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceAberturaDeCaixaClient extends AbstractClientNoClass {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceAberturaDeCaixaClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public Boolean isCaixaOpen(Long id){
        return (Boolean) this.get("/api/integration/cashcheckin/isopen/"+id).getBody();
    }

    public Long getOpenCaixa(){
        Object body = this.get("/api/integration/cashcheckin/opencashcheckin").getBody();
        if(body instanceof Integer){
            return Long.valueOf((Integer)body);
        }else if(body instanceof Long){
            return (Long) body;
        }
        return null;
    }

}
