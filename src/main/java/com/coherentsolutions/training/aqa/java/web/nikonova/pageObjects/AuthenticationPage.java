package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AuthenticationPage {


    @FindBy(how = How.CSS, using = "#create-account_form")
    private WebElement accountCreationForm;

    @FindBy(how = How.CSS, using = "#email_create")
    private WebElement registrationEmailInput;

    @FindBy(how = How.CSS, using = "#SubmitCreate")
    private WebElement registerButton;


    @FindBy(how = How.CSS, using = "#passwd")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using = "#email")
    private WebElement emailInput;

    @FindBy(how = How.CSS, using = "#SubmitLogin")
    private WebElement signinButton;


    public AuthenticationPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);
    }

    public UserRegistrationFormPage registerAccount(String email) {

        this.registrationEmailInput.sendKeys(email);
        this.registerButton.click();
        return new UserRegistrationFormPage();
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(BrowsersSettings.getDriver(), 15L).until(ExpectedConditions.visibilityOf(accountCreationForm)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public AccountPage loginAccount(String username, String password) {
        this.emailInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.signinButton.click();
        return new AccountPage();
    }

}



