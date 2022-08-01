package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AutoWishListTest extends BaseTest {

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
    public void checkWishListIsEmpty() {

        AccountPage accountPage = new AccountPage();
        WishListPage autoWishListPage = accountPage.navigateWishlist();
        Assert.assertEquals(0, autoWishListPage.getWishlistItems().size());
    }

    @Test(priority = 3)

    public void checkProductsPageIsLoaded() {

        WishListPage wishListPage = new WishListPage();
        ProductsPage productsPage = wishListPage.navigateWomenCategory();
        Assert.assertTrue(productsPage.isLoaded(), "Products page was not loaded");
    }

    @Test(priority = 4)
    public void checkProductPageIsLoaded() {

        ProductsPage productsPage = new ProductsPage();
        ProductPage productPage = productsPage.clickFirstProduct();
        Assert.assertTrue(productPage.isLoaded(), "Product page was not loaded");
    }

    @Test(priority = 5)
    public void checkAccountPageIsLoaded() {

        ProductPage productPage = new ProductPage();
        productPage = productPage.addToWishlist();
        AccountPage accountPage = productPage.navigateToAccount();
        Assert.assertTrue(accountPage.isLoaded(), "Account page was not loaded");
    }

    @Test(priority = 6)
    public void checkWishListPageIsLoaded() {

        AccountPage accountPage = new AccountPage();
        WishListPage wishListPage = accountPage.navigateWishlist();
        Assert.assertTrue(wishListPage.isLoaded(), "Wishlist page was not loaded");
    }


    @Test(priority = 7)
    public void checkProductAddedToWishlist() {

        WishListPage wishListPage = new WishListPage();
        wishListPage = wishListPage.expandWishlist();
        Assert.assertTrue(true, "Wishlist was not expanded");
    }
}
