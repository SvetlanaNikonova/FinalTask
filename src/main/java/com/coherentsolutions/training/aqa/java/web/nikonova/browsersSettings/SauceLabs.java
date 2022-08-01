package com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;


public class SauceLabs {

    public static final String USERNAME = "SeleniumTest789";
    public static final String ACCESS_KEY = "c0b18dd7-9f04-4939-bc91-165b444ad486";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";



    public RemoteWebDriver sauceLabsTestOnFirefox() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setPlatform(Platform.WIN10);
        caps.setVersion("39.0");
        return  new RemoteWebDriver(new URL(URL), caps);
    }


    public RemoteWebDriver sauceLabsTestOnChrome() throws MalformedURLException {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setPlatform(Platform.WIN10);
        caps.setVersion("40");
        return new RemoteWebDriver(new URL(URL), caps);
    }
}
