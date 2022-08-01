package com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowsersSettings {

    private static WebDriver driver = null;
    private String environment;
    private String browser;


    public BrowsersSettings(String environment, String browser) {
        this.environment = environment;
        this.browser = browser;
    }

    public static void driverTeardown() {
        driver.quit();
      //  BrowsersSettings.driver = null;
    }

    public static WebDriver getDriver() {
       // if (driver == null) {
        //    driver = new ChromeDriver();
      //  }
        return driver;
    }

    public void setBrowserParameters() throws Exception {
        if(environment == "local")
        {
            if(browser == "chrome")
            {
                driver = new ChromeDriver();
            }
            else if (browser == "firefox"){
                driver = new FirefoxDriver();
            }
            else {throw new Exception("Browser is unavailable");
            }
        }
        else if(environment == "seleniumGrid")
        {
            DesiredCapabilities dr = null;

            if(browser == "chrome")
            {
                dr = DesiredCapabilities.chrome();
                dr.setBrowserName("chrome");
                dr.setPlatform(Platform.WINDOWS);
            }
            else if (browser == "firefox")
            {
                dr = DesiredCapabilities.firefox();
                dr.setBrowserName("firefox");
                dr.setPlatform(Platform.WINDOWS);
            }
            else
            {
                throw new Exception("Browser is unavailable");
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
        }
        else if (environment == "sauceLabs")
        {
            if(browser == "chrome")
            {
                driver = new SauceLabs().sauceLabsTestOnChrome();

            }
            else if (browser == "firefox")
            {
                driver = new SauceLabs().sauceLabsTestOnFirefox();
            }
            else
            {
                throw new Exception("Browser is unavailable");
            }
        }
        else
        {
            throw new Exception("Environment is unavailable");
        }

    }
}
