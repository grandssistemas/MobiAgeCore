package br.com.codein.mobiagecore.application.service.finance;

import br.com.codein.mobiagecore.integration.finance.FinanceTituloClient;
import br.com.grands.financeclient.modelo.titulo.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceIntegrationService {


    private FinanceTituloClient tituloClient;

    @Autowired
    public FinanceIntegrationService(FinanceTituloClient tituloClient) {
        this.tituloClient = tituloClient;
    }


    public Titulo createTitulo(Titulo titulo){

        return tituloClient.createTitulo(titulo);
    }
}
