package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class UserRegisterTest extends BaseTest {

    @DataProvider
    public Object[][] setUser() {
        return new Object[][]{{"Lisa", "Doe", "SeleniumTest789@yandex.com", "password123", "Wall st.", "NY", "55555", "32323232", "Ohio", "Wall st."}};
    }

    @Test(dataProvider = "setUser")
    @Feature("Create account")
    @Description("Verify the ability to create an account")
    public void registerAccountTest(String name, String lastName, String email, String password, String address, String city, String postalCode, String phoneNumber, String state, String alias) throws IOException {
        log.info("Verifying to create an account");
        AuthenticationPage ap = new AuthenticationPage(driver);
        UserRegistrationFormPage userPage = ap.registerAccount(email);
        Assert.assertTrue(userPage.isLoaded(), "Page is not loaded");
        AccountPage accountPage = userPage.fillRegistrationForm(name, lastName, password, address, city, postalCode, phoneNumber, state, alias);
        Assert.assertTrue(accountPage.isLoaded(), "Page is not loaded");
    }
}


