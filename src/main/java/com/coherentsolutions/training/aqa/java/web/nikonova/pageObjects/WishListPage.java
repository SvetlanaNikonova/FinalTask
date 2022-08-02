package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WishListPage {

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

    private By wishlistForm = By.cssSelector("#form_wishlist");

    public WishListPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);

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
            return new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.visibilityOfElementLocated(wishlistForm)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public List<WebElement> getWishlistItems() {
        return this.wishlistItems;
    }

    public ProductsPage navigateWomenCategory() {
        categoryWomen.click();
        return new ProductsPage();
    }

    public boolean isCustomWishlistDisplayed() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.textToBePresentInElement(customWishlist, "Custom Wishlist"));
        } catch (WebDriverException e) {
            return false;
        }
    }
}

