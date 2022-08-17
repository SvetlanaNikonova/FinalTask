package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    @DataProvider
    public Object[][] userLoginDetails() {
       return new Object[][]{
               { "SeleniumTest789@yandex.com", "password123"},
               {"SeleniumTest789@yandex.com", "belekoks_789!"}
       } ;
    }

    @Test(dataProvider = "userLoginDetails")
    @Feature("Login")
    @Description("Verify the ability to login")
    public void loginToAccountTest(String email, String password) throws Exception {
        log.info("Verifying login to account");
        AuthenticationPage ap = new AuthenticationPage(driver);
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }
}

