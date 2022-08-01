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

  //  @FindBy(
  //          css = "h1[itemprop=name]"
  //  )
  //  WebElement productName;
   // @FindBy(
   //         css = "#form_wishlist"
   // )
   // WebElement addToWishlistButton;
    @FindBy(
            css = "#block-history tbody > tr"
    )
    List<WebElement> wishlistItems;
    @FindBy(
            css = "a[title=Women]"
    )
    WebElement categoryWomen;
   // @FindBy(
   //         css = ".account"
   // )
   // WebElement accountButton;
    @FindBy(
            css = ".page-heading"
    )
    WebElement wishlistLink;
   // @FindBy(
    //        css = "#s_title"
   // )
    WebElement wishlistItem;
   // @FindBy(
    //        className = ".page-subheading"
   // )
   // WebElement myWishlist;
    @FindBy(
            id = "block-history"
    )
    WebElement customWishlist;
    @FindBy(
            css = ".lnk_wishlist"
    )
    WebElement wishlistButton;

    public WishListPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);

    }


  //  public void navigateToAccount() {
  //      this.accountButton.click();
  //  }

    public WishListPage expandWishlist() {
        wishlistLink.isDisplayed();
        return this;
    }
    public WishListPage checkWishlist() {
        wishlistButton.click();
        return this;
    }

  //  public String getProductName() {
  //      return this.productName.getText();
  //  }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form_wishlist"))).isDisplayed();
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

  //  public String getWishlistItemName() {
  //      return this.wishlistItem.getText().split("\n")[0];
  //  }

  //  public String getWishlistName() {
  //      return this.wishlistLink.getText();
  //  }
    public boolean isCustomWishlistDisplayed() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.textToBePresentInElement(customWishlist, "Custom Wishlist"));
        } catch (WebDriverException e) {
            return false;
        }
    }
}

