package br.com.codein.mobiagecore.integration.finance;

import br.com.codein.mobiagecore.integration.generic.AbstractClient;
import br.com.grands.financeclient.modelo.titulo.GrupoUnidadeFinanceira;
import br.com.grands.financeclient.modelo.titulo.Pessoa;
import io.gumga.core.GumgaValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceGrupoUnidadeFinanceiraClient extends AbstractClient<GrupoUnidadeFinanceira> {

    private GumgaValues gumgaValues;

    private Properties properties;

    @Autowired
    public FinanceGrupoUnidadeFinanceiraClient(GumgaValues gumgaValues) {
        super();
        this.gumgaValues = gumgaValues;
        this.url = getProperties().getProperty("finance.url");
    }

    private Properties getProperties() {
        if(properties == null)
            properties = gumgaValues.getCustomFileProperties();

        return properties;
    }

    public List<GrupoUnidadeFinanceira> createGruposUnidadeFinanceiras(List<GrupoUnidadeFinanceira> grupoUnidadeFinanceiras){
        return this.post("/api/integration/financeunitgroup/saveintegration", grupoUnidadeFinanceiras).getBody();
    }

}
