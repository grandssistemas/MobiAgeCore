package br.com.codein.mobiagecore.application.component;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

}


