package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @DataProvider
    public Object[][] userLoginDetails() {
        return new Object[][]{{"SeleniumTest789@yandex.com", "password123"}};
    }

    @Test(dataProvider = "userLoginDetails")
    @Feature("Login")
    @Description("Verify the ability to login")
    public void loginToAccountTest(String email, String password) {
        log.info("Verifying login to account");
        AuthenticationPage ap = new AuthenticationPage(driver);
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(dependsOnMethods = "loginToAccountTest")
    @Feature("Create cart with products")
    @Description("Verify the ability to create cart and add products")
    public void checkCartTest() {
        log.info("Verifying creating cart with products");
        WishListPage wishListPage = new WishListPage(driver);
        ProductsPage productsPage = wishListPage.navigateWomenCategory();

        ProductPage cartProductPage = productsPage.clickFirstProduct();
        Assert.assertTrue(cartProductPage.isLoaded(), "Products page was not loaded");
        cartProductPage = cartProductPage.addToCart();
        cartProductPage = cartProductPage.continueShopping();
        log.info("Product page is opened ");

        productsPage = wishListPage.navigateWomenCategory();
        cartProductPage = productsPage.clickSecondProduct();
        cartProductPage = cartProductPage.addToCart();
        log.info("Second product added");
        cartProductPage = cartProductPage.continueShopping();

        productsPage = wishListPage.navigateWomenCategory();
        cartProductPage = productsPage.clickThirdProduct();
        cartProductPage = cartProductPage.addToCart();
        log.info("Third product added");
        cartProductPage = cartProductPage.goToCheckOut();
        Assert.assertTrue(productsPage.isCartDisplayed(), "Cart is not displayed");
    }
}
