package br.com.codein.mobiagecore.gateway.dto;


import br.com.codein.mobiagecore.domain.model.genericreport.enums.ReportType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gelatti on 25/05/17.
 */
public class ReportTypeDTO {

    private String key;
    private String label;

    public ReportTypeDTO(ReportType reportType){
        this.key = reportType.getName();
        this.label = reportType.getLabel();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static List<ReportTypeDTO> getValues(){
        List<ReportTypeDTO> reportTypes = new ArrayList<>();
        Arrays.asList(ReportType.values()).forEach(item->{
            reportTypes.add(new ReportTypeDTO(item));
        });
        return reportTypes;
    }
}
