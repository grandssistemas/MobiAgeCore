package br.com.codein.mobiagecore.application.service.genericreport;

import br.com.codein.mobiagecore.application.repository.genericreport.GenericReportRepository;
import br.com.codein.mobiagecore.domain.model.genericreport.GenericReport;
import br.com.codein.mobiagecore.domain.model.genericreport.enums.ReportType;
import io.gumga.application.GumgaService;
import io.gumga.core.GumgaThreadScope;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import io.gumga.domain.GumgaTenancyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gelatti on 29/06/17.
 */
@Service
public class GenericReportService extends GumgaService<GenericReport, Long> {

    private GenericReportRepository repository;

    @Autowired
    public GenericReportService(GenericReportRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean containsDefault(ReportType type, String name) {
        String hql = "SELECT obj FROM GenericReport obj WHERE obj.oi IS NULL AND obj.name = :name AND obj.reportType = :type";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("type", type);
        SearchResult<GenericReport> result = repository.search(hql, params);
        return !result.getValues().isEmpty();
    }

    public GenericReport getDefaultReport(ReportType type) {
        String hql = String.format("select obj from GenericReport obj where " +
                " (obj.oi is null or obj.oi like '%s%%') and obj.reportType = '%s' " +
                " order by obj.isDefault desc, obj.oi nulls first, obj.id desc", GumgaThreadScope.organizationCode.get(), type.getName());

        SearchResult<GenericReport> result = repository.search(hql, new HashMap<>());

        return result.getValues().isEmpty() ? null : result.getValues().get(0);
    }

    @Override
    public GenericReport save(GenericReport resource) {

        if (resource.getIsDefault()) {
            GenericReport oldDefault = this.getDefaultReport(resource.getReportType());
            if (oldDefault != null && oldDefault.getOi() != null && !oldDefault.getId().equals(resource.getId())) {
                oldDefault.setIsDefault(Boolean.FALSE);
                this.save(oldDefault);
            }
        }

        super.save(resource);
        return resource;
    }
}
