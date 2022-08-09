package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowsersProperties {

    public static String getBrowserProperties(String property) throws IOException {

        final String PATH_BROWSER_PROPERTIES = "./src/main/resources/config/browser.properties";

        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream(PATH_BROWSER_PROPERTIES);
        prop.load(fis);

        return prop.getProperty(property);
    }
}
