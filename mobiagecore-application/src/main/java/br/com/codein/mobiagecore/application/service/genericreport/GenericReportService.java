package br.com.codein.mobiagecore.application.service.genericreport;

import br.com.codein.mobiagecore.application.repository.genericreport.GenericReportRepository;
import br.com.codein.mobiagecore.domain.model.genericreport.GenericReport;
import io.gumga.application.GumgaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
