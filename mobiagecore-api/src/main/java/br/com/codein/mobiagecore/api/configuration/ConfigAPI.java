package br.com.codein.mobiagecore.api.configuration;

import br.com.codein.mobiagecore.application.component.ApplicationConf;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by gelatti on 12/05/17.
 */
@RestController
@RequestMapping("/api/config")
public class ConfigAPI  {

    private ApplicationConf applicationConf = new ApplicationConf();

    @RequestMapping(method = {RequestMethod.GET})
    public Map getConfig() {
        return applicationConf.returnConfig("fashionmanager.properties");
    }
}
