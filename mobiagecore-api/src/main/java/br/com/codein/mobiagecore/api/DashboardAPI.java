package br.com.codein.mobiagecore.api;

import br.com.codein.mobiagecore.domain.model.dashboard.Dashboard;
import io.gumga.application.GumgaService;
import io.gumga.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gelatti on 09/06/17.
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardAPI extends GumgaAPI<Dashboard, Long> {

    @Autowired
    public DashboardAPI(GumgaService<Dashboard, Long> service) {
        super(service);
    }
}
