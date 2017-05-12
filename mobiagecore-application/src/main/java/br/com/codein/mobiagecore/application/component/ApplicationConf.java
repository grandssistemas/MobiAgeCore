package br.com.codein.mobiagecore.application.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gelatti on 10/05/17.
 */
@Component
public class ApplicationConf {

    public Properties getCustomFileProperties(String fileName) {
        Properties toReturn = new Properties();
        try {
            InputStream input = new FileInputStream(System.getProperty("user.home") + "/gumgafiles/" + fileName);
            toReturn.load(input);
        } catch (IOException e) {
            Logger.getLogger(ApplicationConf.class.getName()).log(Level.INFO,"Utilizando properties padrão");
        }
        return toReturn;
    }

    public Map returnConfig(String fileName) {
        Map<String, String> map = new HashMap<>();
        map.put("storageurl", getCustomFileProperties(fileName).getProperty("storage.url"));
        map.put("amazonurl", getCustomFileProperties(fileName).getProperty("storage.amazonurl"));
        map.put("financeurl", getCustomFileProperties(fileName).getProperty("finance.url"));
        map.put("financefronturl", getCustomFileProperties(fileName).getProperty("finance.front.url"));
        return map;
    }

}


