package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class BrowsersSettings {

    private static WebDriver driver = null;
    private String environment;
    private String browser;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;

    public static final String USERNAME = "SeleniumTest789";
    public static final String ACCESS_KEY = "c0b18dd7-9f04-4939-bc91-165b444ad486";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";


    public BrowsersSettings(String environment, String browser) {
        this.environment = environment;
        this.browser = browser;
    }

    public static void driverTeardown() {
        driver.quit();
    }

    public static WebDriver getDriver() {

        return driver;
    }

    public void setBrowserParameters() throws Exception {
        if (environment == BrowsersProperties.GetBrowserProperties("env.local")) {
            if (browser == BrowsersProperties.GetBrowserProperties("browser.chrome")) {
                driver = new ChromeDriver();
            } else if (browser == BrowsersProperties.GetBrowserProperties("browser.firefox")) {
                driver = new FirefoxDriver();
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else if (environment == BrowsersProperties.GetBrowserProperties("env.grid")) {
            //   DesiredCapabilities dr = null;

            if (browser == BrowsersProperties.GetBrowserProperties("browser.chrome")) {
                chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(BrowsersProperties.GetBrowserProperties("grid.chrome.browser.platform"));
                chromeOptions.setBrowserVersion(BrowsersProperties.GetBrowserProperties("grid.chrome.browser.version"));
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            } else if (browser == BrowsersProperties.GetBrowserProperties("browser.firefox")) {
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(BrowsersProperties.GetBrowserProperties("grid.firefox.browser.platform"));
                firefoxOptions.setBrowserVersion(BrowsersProperties.GetBrowserProperties("grid.firefox.browser.version"));
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
            } else {
                throw new Exception("Browser is unavailable");
            }

        } else if (environment == BrowsersProperties.GetBrowserProperties("env.sauceLabs")) {
            if (browser == BrowsersProperties.GetBrowserProperties("browser.chrome")) {
                driver = sauceLabsTestOnChrome();

            } else if (browser == BrowsersProperties.GetBrowserProperties("browser.firefox")) {
                driver = sauceLabsTestOnFirefox();
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else {
            throw new Exception("Environment is unavailable");
        }
    }

    public RemoteWebDriver sauceLabsTestOnFirefox() throws IOException {

        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPlatformName(BrowsersProperties.GetBrowserProperties("sauceLabs.firefox.browser.platform"));
        firefoxOptions.setBrowserVersion(BrowsersProperties.GetBrowserProperties("sauceLabs.firefox.browser.version"));
        return new RemoteWebDriver(new URL(URL), firefoxOptions);

    }

    public RemoteWebDriver sauceLabsTestOnChrome() throws IOException {

        chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName(BrowsersProperties.GetBrowserProperties("sauceLabs.chrome.browser.platform"));
        chromeOptions.setBrowserVersion(BrowsersProperties.GetBrowserProperties("sauceLabs.chrome.browser.version"));
        return new RemoteWebDriver(new URL(URL), chromeOptions);

    }
}
