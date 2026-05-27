package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By checkoutHeading = By.xpath("//*[contains(text(),'Checkout')]");
    private By continueButtons = By.cssSelector("input[value='Continue'], button[type='submit'], .btn-primary");
    private By warning = By.cssSelector(".alert-danger, .text-danger, .invalid-feedback");
    private By successMessage = By.xpath("//*[contains(text(),'Your order has been placed') or contains(text(),'successfully processed')]");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public boolean isCheckoutPageDisplayed() {
        return isDisplayed(checkoutHeading);
    }

    public void continueWithoutDetails() {
        if (count(continueButtons) > 0) {
            elements(continueButtons).get(0).click();
        }
    }

    public boolean isValidationDisplayed() {
        return count(warning) > 0;
    }

    public boolean isOrderSuccessDisplayed() {
        return isDisplayed(successMessage);
    }
}
