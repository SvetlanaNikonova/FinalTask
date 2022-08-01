package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @DataProvider
    public Object[][] userLoginDetails() {
        return new Object[][]{{"SeleniumTest789@yandex.com", "password123"}};
    }

    @Test(dataProvider = "userLoginDetails", priority = 1)
    public void loginToAccount(String email, String password) {

        AuthenticationPage ap = new AuthenticationPage();
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(priority = 2)
    public void checkCartTest() {

        WishListPage wishListPage = new WishListPage();
        ProductsPage productsPage = wishListPage.navigateWomenCategory();
        ProductPage cartProductPage = productsPage.clickFirstProduct();
        cartProductPage = cartProductPage.addToCart();
        cartProductPage = cartProductPage.continueShopping();
        productsPage = wishListPage.navigateWomenCategory();
        cartProductPage = productsPage.clickSecondProduct();
        cartProductPage = cartProductPage.addToCart();
        cartProductPage = cartProductPage.continueShopping();
        productsPage = wishListPage.navigateWomenCategory();
        cartProductPage = productsPage.clickThirdProduct();
        cartProductPage = cartProductPage.addToCart();
        cartProductPage = cartProductPage.goToCheckOut();
        Assert.assertTrue(productsPage.isCartDisplayed(), "Cart is not displayed");


    }
}
