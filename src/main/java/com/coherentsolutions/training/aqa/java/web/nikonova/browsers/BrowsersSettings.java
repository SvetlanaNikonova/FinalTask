package com.coherentsolutions.training.aqa.java.web.nikonova.browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.io.IOException;
import java.net.URL;

public class BrowsersSettings {

    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private String environment;
    private String browser;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;

    public BrowsersSettings(String environment, String browser) {
        this.environment = environment;
        this.browser = browser;
    }

    public static void driverTeardown() {
        getDriver().quit();
        driver.remove();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void setBrowserParameters() throws Exception {
        if (environment.equals(BrowsersProperties.getBrowserProperties("env.local"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                driver.set(ThreadGuard.protect(new ChromeDriver()));
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                driver.set(ThreadGuard.protect(new FirefoxDriver()));
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else if (environment.equals(BrowsersProperties.getBrowserProperties("env.grid"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName(BrowsersProperties.getBrowserProperties(String.format("%s.chrome.browser.platform", environment)));
                chromeOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties(String.format("%s.chrome.browser.version", environment)));
                driver.set(ThreadGuard.protect(new RemoteWebDriver(new URL(BrowsersProperties.getBrowserProperties("url.grid")), chromeOptions)));
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName(BrowsersProperties.getBrowserProperties(String.format("%s.firefox.browser.platform", environment)));
                firefoxOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties(String.format("%s.firefox.browser.version", environment)));
                driver.set(ThreadGuard.protect(new RemoteWebDriver(new URL(BrowsersProperties.getBrowserProperties("url.grid")), firefoxOptions)));
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else if (environment.equals(BrowsersProperties.getBrowserProperties("env.sauceLabs"))) {
            if (browser.equals(BrowsersProperties.getBrowserProperties("browser.chrome"))) {
                driver.set(ThreadGuard.protect(sauceLabsTestOnChrome()));
            } else if (browser.equals(BrowsersProperties.getBrowserProperties("browser.firefox"))) {
                driver.set(ThreadGuard.protect(sauceLabsTestOnFirefox()));
            } else {
                throw new Exception("Browser is unavailable");
            }
        } else {
            throw new Exception("Environment is unavailable");
        }
    }

    private RemoteWebDriver sauceLabsTestOnFirefox() throws IOException {
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        MutableCapabilities sauceOptions = new MutableCapabilities();

        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);

        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPlatformName(BrowsersProperties.getBrowserProperties(String.format("%s.firefox.browser.platform", environment)));
        firefoxOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties(String.format("%s.firefox.browser.version", environment)));
        return new RemoteWebDriver(new URL(BrowsersProperties.getBrowserProperties("url.sauceLabs")), firefoxOptions);
    }

    private RemoteWebDriver sauceLabsTestOnChrome() throws IOException {
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        MutableCapabilities sauceOptions = new MutableCapabilities();

        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);

        chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName(BrowsersProperties.getBrowserProperties(String.format("%s.chrome.browser.platform", environment)));
        chromeOptions.setBrowserVersion(BrowsersProperties.getBrowserProperties(String.format("%s.chrome.browser.version", environment)));
        return new RemoteWebDriver(new URL(BrowsersProperties.getBrowserProperties("url.sauceLabs")), chromeOptions);
    }
}

