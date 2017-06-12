package br.com.codein.mobiagecore.domain.model.dashboard;

import br.com.codein.mobiagecore.domain.model.dashboard.enums.DashboardType;
import io.gumga.domain.GumgaModel;
import io.gumga.domain.GumgaMultitenancy;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by gelatti on 09/06/17.
 */
@GumgaMultitenancy
@SequenceGenerator(name = GumgaModel.SEQ_NAME, sequenceName = "SEQ_DASHBOARD")
@Audited
@Entity
public class Dashboard extends GumgaModel<Long> {

    @Version
    @ApiModelProperty(hidden = true)
    private Integer version;
    @ApiModelProperty(value = "Link para a apresentação salva", position = 0)
    private String linkDashboard;
    @ApiModelProperty(value = "Define se o link é esta ativado ou desativado", position = 1)
    private Boolean active;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Define em qual tela será aberto o dashboard", position = 2)
    private DashboardType dashboardType;
    @ApiModelProperty(value = "Nome para a apresentação salva", position = 0)
    private String name;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLinkDashboard() {
        return linkDashboard;
    }

    public void setLinkDashboard(String linkDashboard) {
        this.linkDashboard = linkDashboard;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DashboardType getDashboardType() {
        return dashboardType;
    }

    public void setDashboardType(DashboardType dashboardType) {
        this.dashboardType = dashboardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
