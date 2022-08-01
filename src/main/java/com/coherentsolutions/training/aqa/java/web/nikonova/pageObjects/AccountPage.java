package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    @FindBy(
            css = "tbody td:first-child a"
    )
    WebElement wishlistLink;
    @FindBy(
            css = ".page-heading"
    )
    WebElement item;

    @FindBy(
            css = ".lnk_wishlist"
    )
    WebElement wishlistButton;
    @FindBy(
            css = "a[title=Women]"
    )
    WebElement categoryWomen;
    @FindBy(
            css = "#name"
    )
    WebElement wishlistNameInput;
    @FindBy(
            css = "#submitWishlist"
    )
    WebElement saveWishlistButton;

    public AccountPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);

    }

    public WishListPage navigateWishlist() {
        wishlistButton.click();
        return new WishListPage();
    }

    public ProductsPage navigateWomenCategory() {
        categoryWomen.click();
        return new ProductsPage();
    }

    public void expandWishlist() {
        this.wishlistLink.click();
    }


    public void createCustomWishlist(String name) {
        this.wishlistNameInput.sendKeys(name);
        this.saveWishlistButton.click();
    }


    public boolean isLoaded() {
        try {
          return   new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-heading"))).isDisplayed();
        }catch (WebDriverException e) {return false;}

    }
}
