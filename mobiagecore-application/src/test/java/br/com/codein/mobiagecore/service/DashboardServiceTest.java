package br.com.codein.mobiagecore.service;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.application.service.dashboard.DashboardService;
import br.com.codein.mobiagecore.domain.model.dashboard.Dashboard;
import br.com.codein.mobiagecore.domain.model.dashboard.enums.DashboardType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gelatti on 12/06/17.
 */
public class DashboardServiceTest extends AbstractTest {

    @Autowired
    private DashboardService service;

    @Test
    public void testSave() {
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Test");
        dashboard.setDashboardType(DashboardType.SALE);
        dashboard.setLinkDashboard("test.test.com.br");
        dashboard.setActive(true);
        service.save(dashboard);
        Assert.assertNotNull(dashboard.getId());
    }

    @Test
    public void testSaveWithOtherActiveSameType() {
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Test");
        dashboard.setDashboardType(DashboardType.SALE);
        dashboard.setLinkDashboard("test.test.com.br");
        dashboard.setActive(true);
        service.save(dashboard);
        Dashboard dashboard2 = new Dashboard();
        dashboard2.setName("Test");
        dashboard2.setDashboardType(DashboardType.SALE);
        dashboard2.setLinkDashboard("test.test.com.br");
        dashboard2.setActive(true);
        service.save(dashboard2);
        Dashboard pesq = service.view(dashboard.getId());
        Dashboard pesq2 = service.view(dashboard2.getId());
        Assert.assertEquals(false, pesq.getActive());
        Assert.assertEquals(true, pesq2.getActive());
    }

    @Test
    public void testSaveWithOtherActiveOtherType() {
        Dashboard dashboard = new Dashboard();
        dashboard.setName("Test");
        dashboard.setDashboardType(DashboardType.CONSIGNMENT);
        dashboard.setLinkDashboard("test.test.com.br");
        dashboard.setActive(true);
        service.save(dashboard);
        Dashboard dashboard2 = new Dashboard();
        dashboard2.setName("Test");
        dashboard2.setDashboardType(DashboardType.SALE);
        dashboard2.setLinkDashboard("test.test.com.br");
        dashboard2.setActive(true);
        service.save(dashboard2);
        Dashboard pesq = service.view(dashboard.getId());
        Dashboard pesq2 = service.view(dashboard2.getId());
        Assert.assertEquals(true, pesq.getActive());
        Assert.assertEquals(true, pesq2.getActive());
    }
}
