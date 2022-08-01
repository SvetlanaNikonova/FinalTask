package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String LOCAL_ENVIRONMENT = "local";
    private static final String SELENIUM_GRID_ENVIRONMENT = "seleniumGrid";
    private static final String SAUCE_LABS_ENVIRONMENT = "sauceLabs";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";


    protected static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

    protected WebDriverWait webdriverwait;

    @BeforeClass
    public void setupWebDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\SvetlanaNikonova\\chromedriver.exe");

        new BrowsersSettings(LOCAL_ENVIRONMENT, CHROME).setBrowserParameters();
        webdriverwait = new WebDriverWait(BrowsersSettings.getDriver(), 30);

        BrowsersSettings.getDriver().manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        BrowsersSettings.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        BrowsersSettings.getDriver().get(URL);
    }

    @AfterClass
    public void cleanup() {

        BrowsersSettings.driverTeardown();
    }

    @AfterMethod
    public void saveScreenshot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot tc = (TakesScreenshot) BrowsersSettings.getDriver();
            File src = tc.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(result.getName() + ".png"));

        //    Allure.addAttachment(new File(saveScreenshot(ITestResult, src)));
        }
    }
}

