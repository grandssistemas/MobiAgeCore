package br.com.codein.mobiagecore.application.service.finance;

import br.com.codein.mobiagecore.integration.finance.FinanceCheckinClient;
import br.com.codein.mobiagecore.integration.finance.FinanceTituloClient;
import br.com.codein.mobiagecore.integration.finance.FinanceUnidadeFinanceiraClient;
import br.com.grands.financeclient.modelo.titulo.Titulo;
import br.com.grands.financeclient.modelo.titulo.UnidadeFinanceira;
import br.com.grands.financeclient.modelo.titulo.enums.TipoUnidadeFinanceira;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by augusto on 14/06/17.
 */
@Component
public class FinanceIntegrationService {


    private FinanceTituloClient tituloClient;

    private FinanceUnidadeFinanceiraClient unidadeFinanceiraClient;

    private FinanceCheckinClient financeCheckinClient;

    @Autowired
    public FinanceIntegrationService(FinanceTituloClient tituloClient,
                                     FinanceUnidadeFinanceiraClient unidadeFinanceiraClient, FinanceCheckinClient financeCheckinClient) {
        this.tituloClient = tituloClient;
        this.unidadeFinanceiraClient = unidadeFinanceiraClient;
        this.financeCheckinClient = financeCheckinClient;
    }


    public List<Titulo> createTitulo(List<Titulo> titulo){
        return tituloClient.createTitulo(titulo);
    }

    public List<UnidadeFinanceira> getFinanceUnits(String name){
        return unidadeFinanceiraClient.getUnidadeFinanceira(name);
    }

    public UnidadeFinanceira createFinanceUnit(UnidadeFinanceira unidadeFinanceira){
        return unidadeFinanceiraClient.createUnidadeFinanceira(unidadeFinanceira);
    }

    public List<UnidadeFinanceira> getFinanceUnitsByAccountType(TipoUnidadeFinanceira account, String nome) {
        return unidadeFinanceiraClient.getUnidadeFinanceiraByAccountTypeAndName(account, nome);
    }


    public ObjectNode createCheckin(ObjectNode node){
        return financeCheckinClient.createCheckin(node);
    }
}
