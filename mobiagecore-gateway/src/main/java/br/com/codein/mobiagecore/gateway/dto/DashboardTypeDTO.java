package br.com.codein.mobiagecore.gateway.dto;

import br.com.codein.mobiagecore.domain.model.dashboard.enums.DashboardType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gelatti on 09/06/17.
 */
public class DashboardTypeDTO {

    private String key;
    private String label;

    public DashboardTypeDTO(DashboardType dashboardType){
        this.key = dashboardType.getName();
        this.label = dashboardType.getLabel();
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

    public static List<DashboardTypeDTO> getValues(){
        List<DashboardTypeDTO> dashboardTypeDTOS = new ArrayList<>();
        Arrays.asList(DashboardType.values()).forEach(item->{
            dashboardTypeDTOS.add(new DashboardTypeDTO(item));
        });
        return dashboardTypeDTOS;
    }

}
