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

public class FailedTestListener implements IInvokedMethodListener {

    public static String screenshotsFolderName;

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
           WebDriver driver = BrowsersSettings.getDriver();
           captureScreenshot(testResult.getTestContext().getSuite().getName() + "_" +
                   testResult.getTestContext().getName() + "_" + testResult.getName() + ".png", driver);
           Allure.addAttachment(testResult.getMethod().getMethodName(),
                   new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
    public static void captureScreenshot(String fileName, WebDriver driver) {
        if (screenshotsFolderName == null) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("ddMMyyyy");
            screenshotsFolderName = now.format(dt);
        }
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("./Screenshots/" + screenshotsFolderName + "/" + fileName);

        try {
            FileUtils.copyFile(srcFile, destFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
