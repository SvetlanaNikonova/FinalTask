package com.coherentsolutions.training.aqa.java.web.nikonova.pageObjects;

import com.coherentsolutions.training.aqa.java.web.nikonova.browsersSettings.BrowsersSettings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AuthenticationPage {


   private static final String AUTH_PAGE_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
   private static final String USER_EMAIL = "SeleniumTest78835@yandex.com";

    @FindBy(how = How.CSS, using = "#create-account_form")
    WebElement accountCreationForm;

    @FindBy(how = How.CSS, using = "#email_create")
    WebElement registrationEmailInput;

    @FindBy(how = How.CSS, using = "#SubmitCreate")
    WebElement registerButton;


    @FindBy(how = How.CSS, using = "#passwd")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using = "#email")
    WebElement emailInput;

    @FindBy(how = How.CSS, using = "#SubmitLogin")
    WebElement signinButton;


    public AuthenticationPage() {

        PageFactory.initElements(BrowsersSettings.getDriver(), this);
    }

    public UserRegistrationFormPage registerAccount() {

        this.registrationEmailInput.sendKeys(USER_EMAIL);
        this.registerButton.click();
        return new UserRegistrationFormPage();
    }

    public boolean isLoaded() {
        return new WebDriverWait(BrowsersSettings.getDriver(), 15L).until(ExpectedConditions.visibilityOf(this.accountCreationForm)).isDisplayed();
    }

    public AccountPage loginAccount(String username, String password) {
        this.emailInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.signinButton.click();
        return new AccountPage();
    }

}



