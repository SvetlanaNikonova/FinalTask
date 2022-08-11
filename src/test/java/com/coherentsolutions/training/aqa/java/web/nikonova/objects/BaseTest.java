package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersProperties;
import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {

    private static final String PATH_BROWSER = "C:\\Users\\SvetlanaNikonova\\chromedriver.exe";

    protected static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    protected WebDriver driver = BrowsersSettings.getDriver();

    public static Logger log = LogManager.getLogger();

    @BeforeSuite
    public void setUp() {
        if (driver == null) {
            log.info("Hello!");
        }
    }

    @AfterSuite
    public void tearDown() {
        log.info("Goodbye!");
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeClass
    public void setupWebDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", PATH_BROWSER);

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


