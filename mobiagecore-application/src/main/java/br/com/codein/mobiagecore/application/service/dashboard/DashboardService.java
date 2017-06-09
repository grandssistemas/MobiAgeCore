package br.com.codein.mobiagecore.application.service.dashboard;

import br.com.codein.mobiagecore.application.repository.dashboard.DashboardRepository;
import br.com.codein.mobiagecore.domain.model.dashboard.Dashboard;
import io.gumga.application.GumgaService;
import io.gumga.domain.repository.GumgaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gelatti on 09/06/17.
 */
@Component
public class DashboardService extends GumgaService<Dashboard, Long> {

    private DashboardRepository repository;

    @Autowired
    public DashboardService(DashboardRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
