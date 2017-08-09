package br.com.codein.mobiagecore.api.integration;

import br.com.codein.mobiagecore.application.service.finance.FinanceIntegrationService;
import br.com.grands.financeclient.modelo.titulo.UnidadeFinanceira;
import br.com.grands.financeclient.modelo.titulo.enums.TipoUnidadeFinanceira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financeintegration")
public class FinanceIntegrationAPI  {

    @Autowired
    private FinanceIntegrationService financeIntegrationService;

    @RequestMapping(value = "/getunidadefinanceira", method = RequestMethod.GET)
    public List<UnidadeFinanceira> getUnidadeFinanceira(@RequestParam String nome){
        return financeIntegrationService.getFinanceUnits(nome);
    }
    @RequestMapping(value = "/getunidadefinanceirabyaccounttype", method = RequestMethod.GET)
    public List<UnidadeFinanceira> getUnidadeFinanceira(@RequestParam TipoUnidadeFinanceira accountType, @RequestParam String nome){
        return financeIntegrationService.getFinanceUnitsByAccountType(accountType, nome);
    }
}
