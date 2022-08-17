package com.coherentsolutions.training.aqa.java.web.nikonova.utils;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.coherentsolutions.training.aqa.java.web.nikonova.objects.BaseTest.log;

public class FailedTestListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

        String str1 = testResult.getTestContext().getSuite().getName();
        String str2 = testResult.getTestContext().getName();
        String str3 = testResult.getName();

        if (testResult.getStatus() == ITestResult.FAILURE) {
            WebDriver driver = BrowsersSettings.getDriver();
            captureScreenshot(String.format("Screenshot" + "%1$s, %2$s, %3$s", str1, str2, str3) + ".png", driver);
            Allure.addAttachment(testResult.getMethod().getMethodName(),
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    public static void captureScreenshot(String fileName, WebDriver driver) {

        String st1 = "./Screenshots/";
        String st2 = "screenshotsFolderName";
        String st3 = fileName;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("ddMMyyyy");
        st2 = now.format(dt);

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("%1$s, %2$s, %3$s", st1, st2, st3));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            log.error("Error while capturing screenshots");
        }
    }
}
