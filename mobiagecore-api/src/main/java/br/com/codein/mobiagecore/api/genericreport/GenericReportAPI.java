package br.com.codein.mobiagecore.api.genericreport;

import br.com.codein.mobiagecore.application.service.genericreport.GenericReportService;
import br.com.codein.mobiagecore.domain.model.genericreport.GenericReport;
import br.com.codein.mobiagecore.domain.model.genericreport.enums.ReportType;
import br.com.codein.mobiagecore.gateway.dto.ReportTypeDTO;
import io.gumga.application.GumgaService;
import io.gumga.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gelatti on 25/05/17.
 */
@RestController
@RequestMapping("/api/genericreport")
public class GenericReportAPI extends GumgaAPI<GenericReport, Long> {

    private GenericReportService genericReportService;

    @Autowired
    public GenericReportAPI(GenericReportService service) {
        super(service);
        this.genericReportService = service;
    }

    @RequestMapping(value = "/getreporttype", method = RequestMethod.GET)
    public List<ReportTypeDTO> getReportType() {
        List<ReportTypeDTO> reportTypeDTOS = new ArrayList<>();
        for (ReportTypeDTO dto : ReportTypeDTO.getValues()) {
            if (!dto.getKey().equals("HEADERFOOTER") && !dto.getKey().equals("CASHCLOSE")) {
                reportTypeDTOS.add(dto);
            }
        }
        return reportTypeDTOS;
    }

    @RequestMapping(value = "/getdefault/{type}", method = RequestMethod.GET)
    public GenericReport getDefault(@PathVariable ReportType type) {
        return this.genericReportService.getDefaultReport(type);
    }


}
