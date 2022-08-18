package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FailedTestsForScreenshots extends BaseTest {

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
    @Feature("Create wishlist")
    @Description("Verify the ability to create wishlist")
    public void checkCustomWishListTest() {
        log.info("Verifying creating custom wishlist");
        AccountPage accountPage = new AccountPage(driver);
        WishListPage autoWishListPage = accountPage.navigateWishlist();
        String customWishlistName = "Custom Wishlist";
        accountPage.createCustomWishlist(customWishlistName);

        WishListPage wishListPage = new WishListPage(driver);
        Assert.assertTrue(wishListPage.isLoaded(), "Wishlist page was not loaded");
        ProductsPage productsPage = wishListPage.navigateWomenCategory();

        ProductsPage customProductsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded(), "Products page was not loaded");
        ProductPage productPage = productsPage.clickFirstProduct();

        ProductPage customProductPage = new ProductPage(driver);
        productPage = productPage.addToWishlist();
        AccountPage customAccountPage = productPage.navigateToAccount();
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");

        WishListPage customWishListPage = new WishListPage(driver);
        wishListPage = wishListPage.expandWishlist().checkWishlist();
        Assert.assertFalse(wishListPage.isCustomWishlistDisplayed(), "Custom Wishlist is not displayed");
    }
}

