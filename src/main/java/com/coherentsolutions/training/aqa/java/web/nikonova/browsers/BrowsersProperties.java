package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowsersProperties {

    public static String getBrowserProperties(String property) throws IOException {

        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("C:\\Users\\SvetlanaNikonova\\IdeaProjects\\FinalTask\\src\\main\\java\\com\\coherentsolutions\\training\\aqa\\java\\web\\nikonova\\config\\browser.properties");
        prop.load(fis);

        return prop.getProperty(property);
    }
}
