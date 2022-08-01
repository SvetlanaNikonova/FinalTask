package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserRegistrationFormPage {

    @FindBy(how = How.CSS, using = "#email_create")
    private WebElement registrationEmailInput;

    @FindBy(how = How.CSS, using = "#SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(how = How.CSS, using = "#customer_firstname")
    private WebElement nameInput;

    @FindBy(how = How.CSS, using = "#customer_lastname")
    private WebElement lastnameInput;

    @FindBy(how = How.CSS, using = "#passwd")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using = "input[id=firstname]")
    private WebElement nameInputAddress;

    @FindBy(how = How.CSS, using = "input[id='lastname']")
    private WebElement lastnameInputAddress;

    @FindBy(how = How.CSS, using = "input[id='address1']")
    private WebElement addressInput;

    @FindBy(how = How.CSS, using = "#city")
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

    public UserRegistrationFormPage() {
        PageFactory.initElements(BrowsersSettings.getDriver(), this);

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
        return new AccountPage();
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 5).until(ExpectedConditions.visibilityOf(accountManagementPanel)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }
}