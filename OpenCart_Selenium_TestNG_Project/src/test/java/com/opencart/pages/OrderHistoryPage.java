package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHistoryPage extends BasePage {
    private By heading = By.xpath("//*[contains(text(),'Order History')]");
    private By orderRows = By.cssSelector("table tbody tr");

    public OrderHistoryPage(WebDriver driver) { super(driver); }

    public boolean isOrderHistoryPageDisplayed() {
        return isDisplayed(heading);
    }

    public int orderCount() {
        return count(orderRows);
    }
}
