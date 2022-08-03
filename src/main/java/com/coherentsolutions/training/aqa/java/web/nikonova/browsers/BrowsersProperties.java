package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowsersProperties {

    public static String GetBrowserProperties(String property) throws IOException {

        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("./src/main/java/com/coherentsolutions/training/aqa/java/web/nikonova/config/browser.properties");
        prop.load(fis);

        return  prop.getProperty(property);
    }
}
