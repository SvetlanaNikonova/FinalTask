package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WishListPage extends BasePage{

    @FindBy(
            css = "#block-history tbody > tr"
    )
    private List<WebElement> wishlistItems;

    @FindBy(
            css = "a[title=Women]"
    )
    private WebElement categoryWomen;

    @FindBy(
            css = ".page-heading"
    )
    private WebElement wishlistLink;

    @FindBy(
            id = "block-history"
    )
    private WebElement customWishlist;

    @FindBy(
            css = ".lnk_wishlist"
    )
    private WebElement wishlistButton;

    @FindBy(
            css = "#form_wishlist"
    )
    private WebElement wishlistForm;

  //  private By wishlistForm = By.cssSelector("#form_wishlist");

    public WishListPage(WebDriver driver) {

        super(driver);

    }

    public WishListPage expandWishlist() {
        wishlistLink.isDisplayed();
        return this;
    }

    public WishListPage checkWishlist() {
        wishlistButton.click();
        return this;
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(wishlistForm)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public List<WebElement> getWishlistItems() {
        return this.wishlistItems;
    }

    public ProductsPage navigateWomenCategory() {
        categoryWomen.click();
        return new ProductsPage(driver);
    }

    public boolean isCustomWishlistDisplayed() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.textToBePresentInElement(customWishlist, "Custom Wishlist"));
        } catch (WebDriverException e) {
            return false;
        }
    }
}

