package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    @DataProvider
    public Object[][] userLoginDetails() {
        return new Object[][]{{"SeleniumTest789@yandex.com", "password123"}};
    }

    @Test(dataProvider = "userLoginDetails")
    public void loginToAccount(String email, String password) {
        AuthenticationPage ap = new AuthenticationPage();
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }
}
