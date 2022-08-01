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

    private By item = By.cssSelector(".page-heading");

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

   // public void expandWishlist() {
   //     this.wishlistLink.click();
   // }


    public AccountPage createCustomWishlist(String name) {
        this.wishlistNameInput.sendKeys(name);
        this.saveWishlistButton.click();
        return this;
    }


    public boolean isLoaded() {
        try {
          return   new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.visibilityOfElementLocated(item)).isDisplayed();
        }catch (WebDriverException e) {return false;}

    }
}
