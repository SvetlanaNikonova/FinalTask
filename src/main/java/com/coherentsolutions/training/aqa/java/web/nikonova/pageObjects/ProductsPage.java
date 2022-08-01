package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductsPage {


    @FindBy(
            xpath = "//*[@class='product-name'][@title='Blouse']"
    )
    private WebElement productFirst;

    @FindBy(
            css = ".product-name[title='Faded Short Sleeve T-shirts']"
    )
    private WebElement secondProduct;
    @FindBy(
            css = ".product-name[title='Printed Chiffon Dress']"
    )
    private WebElement thirdProduct;

    @FindBy(
            id = "cart_title"
    )
    private WebElement cartSummary;

    By productElements = By.cssSelector(".navigation_page");


    public ProductsPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);
    }

    public ProductPage clickFirstProduct() {
        this.productFirst.click();
        return new ProductPage();
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(productElements)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
    public ProductPage clickSecondProduct() {
        this.secondProduct.click();
        return new ProductPage();
    }


    public ProductPage clickThirdProduct() {
        this.thirdProduct.click();
        return new ProductPage();
    }


    public boolean isCartDisplayed() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 10L).until(ExpectedConditions.textToBePresentInElement(cartSummary, "Your shopping cart contains: "));
        } catch (WebDriverException e) {
            return false;
        }
    }
}
