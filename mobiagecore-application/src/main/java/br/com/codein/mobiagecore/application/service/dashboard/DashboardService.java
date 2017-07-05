package br.com.codein.mobiagecore.application.service.dashboard;

import br.com.codein.mobiagecore.application.repository.dashboard.DashboardRepository;
import br.com.codein.mobiagecore.domain.model.dashboard.Dashboard;
import io.gumga.application.GumgaService;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
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

    @Override
    public void beforeSave(Dashboard entity) {
        onlyOneActive(entity);
        super.beforeSave(entity);
    }

    @Override
    public void beforeUpdate(Dashboard entity) {
        onlyOneActive(entity);
        super.beforeUpdate(entity);
    }

    private void onlyOneActive(Dashboard entity) {
        if (entity.getActive()) {
            QueryObject qo = new QueryObject();
            String hql = "obj.dashboardType = '" + entity.getDashboardType() + "'";
            if (entity.getId() != null) {
                hql = hql + " and obj.id <> " + entity.getId();
            }
            qo.setAq(hql);
            SearchResult<Dashboard> searchResult = pesquisa(qo);
            for (Dashboard dashboard : searchResult.getValues()) {
                dashboard.setActive(false);
                save(dashboard);
            }
        }
    }
}
