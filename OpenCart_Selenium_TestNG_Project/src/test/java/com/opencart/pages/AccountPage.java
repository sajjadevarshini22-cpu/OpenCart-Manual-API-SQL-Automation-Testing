package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    private By accountHeading = By.xpath("//*[contains(text(),'My Account')]");
    private By logoutLink = By.linkText("Logout");
    private By orderHistoryLink = By.linkText("Order History");
    private By success = By.cssSelector(".alert-success");

    public AccountPage(WebDriver driver) { super(driver); }

    public boolean isAccountPageDisplayed() {
        return isDisplayed(accountHeading);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(success);
    }

    public void logout() {
        if (count(logoutLink) > 0) click(logoutLink);
    }

    public OrderHistoryPage openOrderHistory() {
        click(orderHistoryLink);
        return new OrderHistoryPage(driver);
    }
}
