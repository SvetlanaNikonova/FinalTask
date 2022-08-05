package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import org.openqa.selenium.MutableCapabilities;
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
        if (environment.equals(BrowsersProperties.getBrowserProperties("env.local"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                driver = new ChromeDriver();
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                driver = new FirefoxDriver();
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else if (environment.equals(BrowsersProperties.getBrowserProperties("env.grid"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(BrowsersProperties.getBrowserProperties("grid.chrome.browser.platform"));
                chromeOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties("grid.chrome.browser.version"));
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(BrowsersProperties.getBrowserProperties("grid.firefox.browser.platform"));
                firefoxOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties("grid.firefox.browser.version"));
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else if (environment.equals(BrowsersProperties.getBrowserProperties("env.sauceLabs"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                driver = sauceLabsTestOnChrome();
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                driver = sauceLabsTestOnFirefox();
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else {
            throw new Exception("Environment is unavailable");
        }
    }

    public RemoteWebDriver sauceLabsTestOnFirefox() throws IOException {

        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        MutableCapabilities sauceOptions = new MutableCapabilities();

        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);

        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPlatformName(BrowsersProperties.getBrowserProperties("sauceLabs.firefox.browser.platform"));
        firefoxOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties("sauceLabs.firefox.browser.version"));

        return new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), firefoxOptions);

    }

    public RemoteWebDriver sauceLabsTestOnChrome() throws IOException {

        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        MutableCapabilities sauceOptions = new MutableCapabilities();

        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);

        chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName(BrowsersProperties.getBrowserProperties("sauceLabs.chrome.browser.platform"));
        chromeOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties("sauceLabs.chrome.browser.version"));
        return new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), chromeOptions);

    }
}

