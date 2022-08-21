package com.coherentsolutions.training.aqa.java.web.nikonova.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthenticationPage extends BasePage {

    @FindBy(css = "#create-account_form")
    private WebElement accountCreationForm;

    @FindBy(css = "#email_create")
    private WebElement registrationEmailInput;

    @FindBy(css = "#SubmitCreate")
    private WebElement registerButton;

    @FindBy(css = "#passwd")
    private WebElement passwordInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#SubmitLogin")
    private WebElement signinButton;

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public UserRegistrationFormPage registerAccount(String email) {
        registrationEmailInput.sendKeys(email);
        registerButton.click();
        return new UserRegistrationFormPage(driver);
    }

    public boolean isOpened() {
        try {
            return new WebDriverWait(driver,
                    Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(signinButton)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isLoaded() {
        try {
            return new WebDriverWait(driver,
                    Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(accountCreationForm)).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public AccountPage loginAccount(String username, String password) {
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signinButton.click();
        return new AccountPage(driver);
    }
}



