package com.opencart.pages;

import com.opencart.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By email = By.name("email");
    private By telephone = By.name("telephone");
    private By password = By.name("password");
    private By confirmPassword = By.name("confirm");
    private By privacyPolicy = By.name("agree");
    private By continueButton = By.cssSelector("input[type='submit'], button[type='submit']");
    private By successHeading = By.xpath("//*[contains(text(),'Your Account Has Been Created') or contains(text(),'successfully')]");
    private By warning = By.cssSelector(".alert-danger, .text-danger, .invalid-feedback");

    public RegisterPage(WebDriver driver) { super(driver); }

    public void submitBlankForm() {
        click(continueButton);
    }

    public AccountPage registerValidUser(String emailAddress) {
        type(firstName, Config.FIRST_NAME);
        type(lastName, Config.LAST_NAME);
        type(email, emailAddress);
        if (count(telephone) > 0) type(telephone, Config.TELEPHONE);
        type(password, Config.PASSWORD);
        type(confirmPassword, Config.PASSWORD);
        if (count(privacyPolicy) > 0) click(privacyPolicy);
        click(continueButton);
        return new AccountPage(driver);
    }

    public boolean isValidationDisplayed() {
        return count(warning) > 0;
    }

    public boolean isRegistrationSuccessful() {
        return isDisplayed(successHeading);
    }
}
