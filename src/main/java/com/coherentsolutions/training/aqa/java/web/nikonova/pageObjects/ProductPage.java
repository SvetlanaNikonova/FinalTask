package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ProductPage {

    @FindBy(
            xpath = "//a[@id='wishlist_button']"
    )
    WebElement addToWishlistButton;
    //  @FindBy(
    //         css = "#block-history tbody > tr"
    // )
    //  List<WebElement> wishlistItems;
    //  @FindBy(
    //         css = "a[title='Women']"
    // )
    // WebElement categoryWomen;
    @FindBy(
            css = ".account"
    )
    WebElement accountButton;
    @FindBy(
            css = "a[title='Close']"
    )
    WebElement closeButton;
    @FindBy(
            id = "product_reference"
    )
    WebElement itemsButton;
    @FindBy(
            id = "add_to_cart"
    )
    WebElement addToCart;
    @FindBy(
            css = "div.button-container>span"
    )
    WebElement continueShopping;
    @FindBy(
            css = "div.button-container > a > span"
    )
    WebElement checkOut;

    public ProductPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);

    }

    public ProductPage addToWishlist() {

        this.addToWishlistButton.click();
        closeButton.click();
        return this;
    }
    public ProductPage addToCart() {
        this.addToCart.click();
        return new ProductPage();
    }
    public ProductPage continueShopping() {
        this.continueShopping.click();
        return new ProductPage();
    }

    public ProductPage goToCheckOut() {
        this.checkOut.click();
        return new ProductPage();
    }

    public AccountPage navigateToAccount() {
        this.accountButton.click();
        return new AccountPage();
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(By.id("product_reference"))).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}