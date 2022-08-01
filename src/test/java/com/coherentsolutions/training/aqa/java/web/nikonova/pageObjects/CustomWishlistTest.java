package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomWishlistTest extends BaseTest {

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
    public void checkCustomWishList() {

        AccountPage accountPage = new AccountPage();
        WishListPage autoWishListPage = accountPage.navigateWishlist();
        //    Assert.assertEquals(0, autoWishListPage.getWishlistItems().size());
        String customWishlistName = "Custom Wishlist";
        accountPage.createCustomWishlist(customWishlistName);
        WishListPage wishListPage = new WishListPage();
        ProductsPage productsPage = wishListPage.navigateWomenCategory();
        ProductsPage customProductsPage = new ProductsPage();
        ProductPage productPage = productsPage.clickFirstProduct();
        ProductPage customProductPage = new ProductPage();
        productPage = productPage.addToWishlist();
        AccountPage customAccountPage = productPage.navigateToAccount();
        WishListPage customWishListPage = new WishListPage();
        wishListPage = wishListPage.expandWishlist().checkWishlist();
        Assert.assertTrue(wishListPage.isCustomWishlistDisplayed(), "Custom Wishlist is not displayed");
    }
}

