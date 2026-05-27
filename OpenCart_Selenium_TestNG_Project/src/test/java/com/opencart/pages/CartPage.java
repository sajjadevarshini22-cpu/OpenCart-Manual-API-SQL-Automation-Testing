package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By cartTable = By.cssSelector(".table, form table");
    private By quantityInput = By.cssSelector("input[name*='quantity']");
    private By updateButton = By.cssSelector("button[data-original-title='Update'], button[title='Update'], button[type='submit']");
    private By removeButton = By.cssSelector("button[data-original-title='Remove'], button[title='Remove'], .btn-danger");
    private By emptyCartMessage = By.xpath("//*[contains(text(),'Your shopping cart is empty') or contains(text(),'empty')]");
    private By checkoutButton = By.linkText("Checkout");

    public CartPage(WebDriver driver) { super(driver); }

    public boolean isProductDisplayedInCart() {
        return isDisplayed(cartTable);
    }

    public void updateQuantity(String qty) {
        type(quantityInput, qty);
        click(updateButton);
    }

    public void removeProduct() {
        click(removeButton);
    }

    public boolean isEmptyCartDisplayed() {
        return isDisplayed(emptyCartMessage);
    }

    public CheckoutPage goToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
