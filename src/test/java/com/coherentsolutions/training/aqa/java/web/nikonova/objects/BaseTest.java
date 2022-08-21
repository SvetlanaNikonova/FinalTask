package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersProperties;
import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    private static final String PATH_BROWSER = "./src/main/resources/drivers/chromedriver.exe";

    protected static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    protected WebDriver driver;

    public static Logger log = LogManager.getLogger();

    @BeforeClass
    public void setupWebDriver() throws Exception {
        log.info("Tests are starting!");
        System.setProperty("webdriver.chrome.driver", PATH_BROWSER);

        new BrowsersSettings(BrowsersProperties.getBrowserProperties("env.local"), (BrowsersProperties.getBrowserProperties("browser.chrome"))).setBrowserParameters();
    }

    @AfterClass
    public void cleanup() {
        log.info("Tests are ending!");
        BrowsersSettings.driverTeardown();
    }

    public AuthenticationPage isAuthenticationPageOpened() {
        driver = BrowsersSettings.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.isOpened();
        return new AuthenticationPage(driver);
    }
}


