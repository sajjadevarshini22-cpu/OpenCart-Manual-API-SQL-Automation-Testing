package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private By productHeading = By.cssSelector("h1");
    private By quantity = By.name("quantity");
    private By addToCart = By.id("button-cart");
    private By success = By.cssSelector(".alert-success");
    private By cartButton = By.cssSelector("#cart button, button[data-bs-toggle='dropdown']");
    private By viewCart = By.xpath("//strong[contains(text(),'View Cart')]/ancestor::a | //a[contains(.,'View Cart')]");

    public ProductPage(WebDriver driver) { super(driver); }

    public boolean isProductHeadingDisplayed() {
        return isDisplayed(productHeading);
    }

    public void setQuantity(String qty) {
        if (count(quantity) > 0) type(quantity, qty);
    }

    public void addToCart() {
        click(addToCart);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(success);
    }

    public CartPage viewCartFromMiniCart() {
        click(cartButton);
        click(viewCart);
        return new CartPage(driver);
    }
}
