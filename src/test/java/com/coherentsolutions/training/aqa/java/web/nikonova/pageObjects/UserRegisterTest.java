package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class UserRegisterTest extends BaseTest {


    @DataProvider
    public Object[][] setUser() {
        return new Object[][]{{"Lisa", "Doe", "SeleniumTest78529@yandex.com", "password123", "Wall st.", "NY", "55555", "32323232", "Ohio", "Wall st."}};
    }


    @Test(dataProvider = "setUser")
    public void registerAccount(String name, String lastName, String email, String password, String address, String city, String postalCode, String phoneNumber, String state, String alias) throws IOException {

        AuthenticationPage ap = new AuthenticationPage();
        UserRegistrationFormPage userPage = ap.registerAccount(email);
        Assert.assertTrue(userPage.isLoaded());
        AccountPage accountPage = userPage.fillRegistrationForm(name, lastName, password, address, city, postalCode, phoneNumber, state, alias);
        Assert.assertTrue(accountPage.isLoaded());
    }
}


