package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsers.BrowsersSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//*[@class='product-name'][@title='Blouse']")
    private WebElement productFirst;

    @FindBy(css = ".product-name[title='Faded Short Sleeve T-shirts']")
    private WebElement secondProduct;

    @FindBy(css = ".product-name[title='Printed Chiffon Dress']")
    private WebElement thirdProduct;

    @FindBy(id = "cart_title")
    private WebElement cartSummary;

    @FindBy(css = ".navigation_page")
    private WebElement productElements;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickFirstProduct() {
        productFirst.click();
        return new ProductPage(driver);
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(driver,
                    Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(productElements)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public ProductPage clickSecondProduct() {
        secondProduct.click();
        return new ProductPage(driver);
    }

    public ProductPage clickThirdProduct() {
        thirdProduct.click();
        return new ProductPage(driver);
    }

    public boolean isCartDisplayed() {
        try {
            return new WebDriverWait(driver,
                    Duration.ofSeconds(5)).until(ExpectedConditions.textToBePresentInElement(cartSummary, "Your shopping cart contains: "));
        } catch (WebDriverException e) {
            return false;
        }
    }
}
