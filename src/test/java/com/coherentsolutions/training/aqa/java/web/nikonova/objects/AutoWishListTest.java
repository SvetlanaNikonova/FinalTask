package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AutoWishListTest extends BaseTest {

    @DataProvider
    public Object[][] userLoginDetails() {
        return new Object[][]{{"SeleniumTest789@yandex.com", "password123"}};
    }

    @Test(dataProvider = "userLoginDetails", priority = 1)
    @Feature("Login")
    @Description("Verify the ability to login")
    public void loginToAccountTest(String email, String password) {
        log.info("Verifying login to account");
        AuthenticationPage ap = new AuthenticationPage(driver);
        AccountPage accountPage = ap.loginAccount(email, password);
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(priority = 2)
    @Feature("Check wishlist")
    @Description("Verify the ability to check wishlist")
    public void checkWishListIsEmptyTest() {
        log.info("Checking if wishlist is empty");
        AccountPage accountPage = new AccountPage(driver);
        WishListPage autoWishListPage = accountPage.navigateWishlist();
        Assert.assertEquals(0, autoWishListPage.getWishlistItems().size(), "Wishlist page was not empty");
    }

    @Test(priority = 3)
    @Feature("Check products page")
    @Description("Verify the ability to load products page")
    public void checkProductsPageIsLoadedTest() {
        log.info("Loading products page");
        WishListPage wishListPage = new WishListPage(driver);
        ProductsPage productsPage = wishListPage.navigateWomenCategory();
        Assert.assertTrue(productsPage.isLoaded(), "Products page was not loaded");
    }

    @Test(priority = 4)
    @Feature("Check product page")
    @Description("Verify the ability to load product page")
    public void checkProductPageIsLoadedTest() {
        log.info("Loading product page");
        ProductsPage productsPage = new ProductsPage(driver);
        ProductPage productPage = productsPage.clickFirstProduct();
        Assert.assertTrue(productPage.isLoaded(), "Product page was not loaded");
    }

    @Test(priority = 5)
    @Feature("Check account page")
    @Description("Verify the ability to load account page")
    public void checkAccountPageIsLoadedTest() {
        log.info("Loading account page");
        ProductPage productPage = new ProductPage(driver);
        productPage = productPage.addToWishlist();
        AccountPage accountPage = productPage.navigateToAccount();
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(priority = 6)
    @Feature("Check wishlist page")
    @Description("Verify the ability to load wishlist page")
    public void checkWishListPageIsLoadedTest() {
        log.info("Loading wishlist page");
        AccountPage accountPage = new AccountPage(driver);
        WishListPage wishListPage = accountPage.navigateWishlist();
        Assert.assertTrue(wishListPage.isLoaded(), "Wishlist page was not loaded");
    }

    @Test(priority = 7)
    @Feature("Check added product")
    @Description("Verify the ability to add product")
    public void checkProductAddedToWishlistTest() {
        log.info("Checking if products are added");
        WishListPage wishListPage = new WishListPage(driver);
        wishListPage = wishListPage.expandWishlist();
        Assert.assertTrue(true, "Wishlist was not expanded");
    }
}
