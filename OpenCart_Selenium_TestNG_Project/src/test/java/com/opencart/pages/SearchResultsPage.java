package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage {
    private By productCards = By.cssSelector(".product-thumb, .product-layout");
    private By noResults = By.xpath("//*[contains(text(),'no product') or contains(text(),'There is no product')]");
    private By firstProductName = By.cssSelector(".product-thumb .description h4 a, .product-layout h4 a");

    public SearchResultsPage(WebDriver driver) { super(driver); }

    public int productCount() {
        return count(productCards);
    }

    public boolean isNoResultsDisplayed() {
        return isDisplayed(noResults);
    }

    public ProductPage openFirstProduct() {
        click(firstProductName);
        return new ProductPage(driver);
    }
}
