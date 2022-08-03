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

    @Test(dataProvider = "userLoginDetails", priority = 1)
    @Feature("Login")
    @Description("Verify the ability to login")
    public void loginToAccount(String email, String password) {

        AuthenticationPage ap = new AuthenticationPage(driver);
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(priority = 2)
    @Feature("Create wishlist")
    @Description("Verify the ability to create wishlist")
    public void checkCustomWishList() {

        AccountPage accountPage = new AccountPage(driver);
        WishListPage autoWishListPage = accountPage.navigateWishlist();
        String customWishlistName = "Custom Wishlist";
        accountPage.createCustomWishlist(customWishlistName);
        WishListPage wishListPage = new WishListPage(driver);
        ProductsPage productsPage = wishListPage.navigateWomenCategory();
        ProductsPage customProductsPage = new ProductsPage(driver);
        ProductPage productPage = productsPage.clickFirstProduct();
        ProductPage customProductPage = new ProductPage(driver);
        productPage = productPage.addToWishlist();
        AccountPage customAccountPage = productPage.navigateToAccount();
        WishListPage customWishListPage = new WishListPage(driver);
        wishListPage = wishListPage.expandWishlist().checkWishlist();
        Assert.assertFalse(wishListPage.isCustomWishlistDisplayed(), "Custom Wishlist is not displayed");
    }
}

