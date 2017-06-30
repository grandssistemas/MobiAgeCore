package br.com.codein.mobiagecore.api.genericreport;

import br.com.codein.mobiagecore.application.service.genericreport.JSDataAdapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Copyright Stimulsoft
 */
@RestController
@RequestMapping("/api/genericreport/reportconnection")
public class JSDataAdapterAPI {

    private JSDataAdapterService jsDataAdapterService;

    @Autowired
    public JSDataAdapterAPI(JSDataAdapterService jsDataAdapterService) {
        super();
        this.jsDataAdapterService = jsDataAdapterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String result = jsDataAdapterService.process(req.getInputStream());
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Cache-Control", "no-cache");
            resp.getOutputStream().write(result.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
