package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By email = By.name("email");
    private By password = By.name("password");
    private By loginButton = By.cssSelector("input[type='submit'], button[type='submit']");
    private By warning = By.cssSelector(".alert-danger, .text-danger");

    public LoginPage(WebDriver driver) { super(driver); }

    public AccountPage login(String emailAddress, String pwd) {
        type(email, emailAddress);
        type(password, pwd);
        click(loginButton);
        return new AccountPage(driver);
    }

    public void loginInvalid(String emailAddress, String pwd) {
        type(email, emailAddress);
        type(password, pwd);
        click(loginButton);
    }

    public boolean isWarningDisplayed() {
        return isDisplayed(warning);
    }
}
