package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By myAccount = By.xpath("//span[contains(text(),'My Account')]/ancestor::a | //a[contains(.,'My Account')]");
    private By register = By.linkText("Register");
    private By login = By.linkText("Login");
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-light, #search button, button[type='button']");
    private By cartLink = By.xpath("//span[contains(text(),'Shopping Cart')]/ancestor::a | //a[contains(.,'Shopping Cart')]");
    private By logo = By.cssSelector("img[title='Your Store'], #logo img");

    public HomePage(WebDriver driver) { super(driver); }

    public boolean isLogoVisible() {
        return isDisplayed(logo);
    }

    public RegisterPage openRegisterPage() {
        click(myAccount);
        click(register);
        return new RegisterPage(driver);
    }

    public LoginPage openLoginPage() {
        click(myAccount);
        click(login);
        return new LoginPage(driver);
    }

    public SearchResultsPage searchProduct(String keyword) {
        type(searchBox, keyword);
        click(searchButton);
        return new SearchResultsPage(driver);
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
