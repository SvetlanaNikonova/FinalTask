package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//a[@id='wishlist_button']")
    private WebElement addToWishlistButton;

    @FindBy(css = ".account")
    private WebElement accountButton;

    @FindBy(css = "a[title='Close']")
    private WebElement closeButton;

    @FindBy(id = "add_to_cart")
    private WebElement addToCart;

    @FindBy(css = "div.button-container>span")
    private WebElement continueShopping;

    @FindBy(css = "div.button-container > a > span")
    private WebElement checkOut;

    @FindBy(id = "product_reference")
    private WebElement itemsButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToWishlist() {
        addToWishlistButton.click();
        closeButton.click();
        return this;
    }

    public ProductPage addToCart() {
        addToCart.click();
        return new ProductPage(driver);
    }

    public ProductPage continueShopping() {
        continueShopping.click();
        return new ProductPage(driver);
    }

    public ProductPage goToCheckOut() {
        checkOut.click();
        return new ProductPage(driver);
    }

    public AccountPage navigateToAccount() {
        accountButton.click();
        return new AccountPage(driver);
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(itemsButton)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}