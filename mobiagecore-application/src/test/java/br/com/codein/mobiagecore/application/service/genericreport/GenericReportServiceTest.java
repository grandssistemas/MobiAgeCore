package br.com.codein.mobiagecore.application.service.genericreport;

import br.com.codein.mobiagecore.AbstractTest;
import br.com.codein.mobiagecore.domain.model.genericreport.GenericReport;
import br.com.codein.mobiagecore.domain.model.genericreport.enums.ReportType;
import io.gumga.core.GumgaThreadScope;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import io.gumga.domain.GumgaTenancyUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class GenericReportServiceTest extends AbstractTest {

    @Autowired
    private GenericReportService genericReportService;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testGetDefault(){

        GenericReport r1 = new GenericReport();
        r1.setIsDefault(Boolean.FALSE);
        r1.setReportType(ReportType.PRINTSALE);
        r1.setName("r1");
        genericReportService.save(r1);

        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.TRUE);
        r2.setName("r1");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        GenericReport result = genericReportService.getDefaultReport(ReportType.PRINTSALE);

        assertNotNull(result);
        assertEquals(r2.getId(), result.getId());
    }

    @Test
    public void testGetDefaultPublic(){
        GenericReport r1 = new GenericReport();
        GumgaTenancyUtils.changeOi(null, r1);
        r1.setIsDefault(Boolean.FALSE);
        r1.setReportType(ReportType.PRINTSALE);
        r1.setName("r1");
        genericReportService.save(r1);

        assertNotNull(r1);
        assertNull(r1.getOi().getValue());

        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.FALSE);
        r2.setName("r2");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        GenericReport result = genericReportService.getDefaultReport(ReportType.PRINTSALE);

        assertNotNull(result);
        assertEquals(r1.getId(), result.getId());
    }

    @Test
    public void testGetDefaultPublic2(){
        GenericReport r1 = new GenericReport();
        GumgaTenancyUtils.changeOi(null, r1);
        r1.setIsDefault(Boolean.FALSE);
        r1.setReportType(ReportType.PRINTSALE);
        r1.setName("r1");
        genericReportService.save(r1);

        assertNotNull(r1);
        assertNull(r1.getOi().getValue());

        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.TRUE);
        r2.setName("r2");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        GenericReport result = genericReportService.getDefaultReport(ReportType.PRINTSALE);

        assertNotNull(result);
        assertEquals(r2.getId(), result.getId());
    }
    @Test
    public void testGetDefault2(){

        GenericReport r1 = new GenericReport();
        r1.setIsDefault(Boolean.FALSE);
        r1.setReportType(ReportType.SALE);
        r1.setName("r1");
        genericReportService.save(r1);

        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.TRUE);
        r2.setName("r1");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        GenericReport result = genericReportService.getDefaultReport(ReportType.SALE);

        assertNotNull(result);
        assertEquals(r1.getId(), result.getId());
    }


    @Test
    public void testRemoveDefault(){

        GenericReport r1 = new GenericReport();
        r1.setIsDefault(Boolean.TRUE);
        r1.setReportType(ReportType.PRINTSALE);
        r1.setName("r1");
        genericReportService.save(r1);
        assertTrue(r1.getIsDefault());

        assertNotNull(r1);
        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.TRUE);
        r2.setName("r1");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        assertNotNull(r2);

        assertFalse(r1.getIsDefault());
        assertTrue(r2.getIsDefault());
    }


    @Test
    public void testDontRemoveDefault(){

        GenericReport r1 = new GenericReport();
        r1.setIsDefault(Boolean.TRUE);
        r1.setReportType(ReportType.SALE);
        r1.setName("r1");
        genericReportService.save(r1);
        assertTrue(r1.getIsDefault());

        assertNotNull(r1);
        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.TRUE);
        r2.setName("r1");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        assertNotNull(r2);

        assertTrue(r1.getIsDefault());
        assertTrue(r2.getIsDefault());
    }


    @Test
    public void testDontRemoveDefault2(){

        GenericReport r1 = new GenericReport();
        r1.setIsDefault(Boolean.FALSE);
        r1.setReportType(ReportType.PRINTSALE);
        r1.setName("r1");
        genericReportService.save(r1);
        assertFalse(r1.getIsDefault());

        assertNotNull(r1);
        GenericReport r2 = new GenericReport();
        r2.setIsDefault(Boolean.FALSE);
        r2.setName("r1");
        r2.setReportType(ReportType.PRINTSALE);
        genericReportService.save(r2);

        assertNotNull(r2);

        assertFalse(r1.getIsDefault());
        assertFalse(r2.getIsDefault());
    }



}