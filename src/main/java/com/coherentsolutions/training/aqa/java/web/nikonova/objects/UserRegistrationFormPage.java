package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserRegistrationFormPage extends BasePage {

    @FindBy(css = "#email_create")
    private WebElement registrationEmailInput;

    @FindBy(css = "#SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(css = "#customer_firstname")
    private WebElement nameInput;

    @FindBy(css = "#customer_lastname")
    private WebElement lastnameInput;

    @FindBy(css = "#passwd")
    private WebElement passwordInput;

    @FindBy(css = "input[id=firstname]")
    private WebElement nameInputAddress;

    @FindBy(css = "input[id='lastname']")
    private WebElement lastnameInputAddress;

    @FindBy(css = "input[id='address1']")
    private WebElement addressInput;

    @FindBy(css = "#city")
    private WebElement cityInput;

    @FindBy(css = "select[id='id_state']")
    private WebElement stateSelect;

    @FindBy(css = "input[id='postcode']")
    private WebElement postalCodeInput;

    @FindBy(css = "input[id='phone_mobile']")
    private WebElement phoneNumberInput;

    @FindBy(css = "input[id='alias']")
    private WebElement alias;

    @FindBy(css = "button[id='submitAccount']")
    private WebElement registerButton;

    @FindBy(css = "div #center_column")
    private WebElement accountManagementPanel;

    public UserRegistrationFormPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage fillRegistrationForm(String name, String lastName, String password, String address, String city, String postalCode, String phoneNumber, String state, String alias) {
        nameInput.sendKeys(name);
        lastnameInput.sendKeys(lastName);
        passwordInput.sendKeys(password);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);

        Select select = new Select(stateSelect);
        select.selectByVisibleText(state);
        postalCodeInput.sendKeys(postalCode);
        phoneNumberInput.sendKeys(phoneNumber);
        this.alias.sendKeys(alias);

        registerButton.click();
        return new AccountPage(driver);
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(driver,
                    Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(accountManagementPanel)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}