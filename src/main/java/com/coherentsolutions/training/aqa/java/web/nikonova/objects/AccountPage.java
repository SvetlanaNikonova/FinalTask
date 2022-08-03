package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage{

    @FindBy(
            css = "tbody td:first-child a"
    )
    private WebElement wishlistLink;

    @FindBy(
            css = ".lnk_wishlist"
    )
    private WebElement wishlistButton;

    @FindBy(
            css = "a[title=Women]"
    )
    private WebElement categoryWomen;

    @FindBy(
            css = "#name"
    )
    private WebElement wishlistNameInput;

    @FindBy(
            css = "#submitWishlist"
    )
    private WebElement saveWishlistButton;

    @FindBy(
            css = ".page-heading"
    )
    private WebElement item;

   // private By item = By.cssSelector(".page-heading");

    public AccountPage(WebDriver driver) {
        super(driver);


    }

    public WishListPage navigateWishlist() {
        wishlistButton.click();
        return new WishListPage(driver);
    }

    public ProductsPage navigateWomenCategory() {
        categoryWomen.click();
        return new ProductsPage(driver);
    }

    public AccountPage createCustomWishlist(String name) {
        this.wishlistNameInput.sendKeys(name);
        this.saveWishlistButton.click();
        return this;
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(item)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}
