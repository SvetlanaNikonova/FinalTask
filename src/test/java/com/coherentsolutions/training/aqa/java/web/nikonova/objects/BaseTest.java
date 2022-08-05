package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersProperties;
import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {

    protected static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    protected WebDriver driver = BrowsersSettings.getDriver();

    @BeforeClass
    public void setupWebDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\SvetlanaNikonova\\chromedriver.exe");

        new BrowsersSettings(BrowsersProperties.getBrowserProperties("env.local"), (BrowsersProperties.getBrowserProperties("browser.chrome"))).setBrowserParameters();

        BrowsersSettings.getDriver().manage().window().maximize();
        BrowsersSettings.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        BrowsersSettings.getDriver().get(URL);
    }

    @AfterClass
    public void cleanup() {

        BrowsersSettings.driverTeardown();
    }
}


