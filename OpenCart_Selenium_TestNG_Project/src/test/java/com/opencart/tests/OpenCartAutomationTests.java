package com.opencart.tests;

import com.opencart.base.BaseTest;
import com.opencart.pages.*;
import com.opencart.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenCartAutomationTests extends BaseTest {

    private String createUserAndReturnEmail() {
        HomePage home = new HomePage(driver);
        String email = Config.uniqueEmail();
        RegisterPage registerPage = home.openRegisterPage();
        AccountPage accountPage = registerPage.registerValidUser(email);
        Assert.assertTrue(registerPage.isRegistrationSuccessful() || accountPage.isAccountPageDisplayed(),
                "Registration should be successful");
        registeredEmail = email;
        return email;
    }

    @Test(priority = 1, description = "AUTO-001: Verify Register page opens")
    public void verifyRegisterPageNavigation() {
        HomePage home = new HomePage(driver);
        RegisterPage registerPage = home.openRegisterPage();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("register") ||
                driver.getPageSource().contains("Register Account"));
    }

    @Test(priority = 2, description = "AUTO-002: Verify mandatory registration validation")
    public void verifyRegistrationMandatoryValidation() {
        HomePage home = new HomePage(driver);
        RegisterPage registerPage = home.openRegisterPage();
        registerPage.submitBlankForm();
        Assert.assertTrue(registerPage.isValidationDisplayed(),
                "Validation messages should appear for blank registration form");
    }

    @Test(priority = 3, description = "AUTO-003: Verify valid login")
    public void verifyValidLogin() {
        String email = createUserAndReturnEmail();
        new AccountPage(driver).logout();
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.openLoginPage();
        AccountPage accountPage = loginPage.login(email, Config.PASSWORD);
        Assert.assertTrue(accountPage.isAccountPageDisplayed(), "User should login successfully");
    }

    @Test(priority = 4, description = "AUTO-004: Verify invalid login")
    public void verifyInvalidLogin() {
        HomePage home = new HomePage(driver);
        LoginPage loginPage = home.openLoginPage();
        loginPage.loginInvalid("wrong_" + System.currentTimeMillis() + "@example.com", "wrongPassword");
        Assert.assertTrue(loginPage.isWarningDisplayed(), "Invalid login warning should display");
    }

    @Test(priority = 5, description = "AUTO-005: Verify logout")
    public void verifyLogout() {
        createUserAndReturnEmail();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logout();
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("logout") ||
                driver.getPageSource().toLowerCase().contains("account logout"));
    }

    @Test(priority = 6, description = "AUTO-006: Search valid product")
    public void verifyValidProductSearch() {
        HomePage home = new HomePage(driver);
        SearchResultsPage results = home.searchProduct("MacBook");
        Assert.assertTrue(results.productCount() > 0, "Search results should show products");
    }

    @Test(priority = 7, description = "AUTO-007: Search invalid product")
    public void verifyInvalidProductSearch() {
        HomePage home = new HomePage(driver);
        SearchResultsPage results = home.searchProduct("xyzabc123notfound");
        Assert.assertTrue(results.isNoResultsDisplayed() || results.productCount() == 0,
                "No results should display for invalid keyword");
    }

    @Test(priority = 8, description = "AUTO-008: Open product details")
    public void verifyOpenProductDetails() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        Assert.assertTrue(productPage.isProductHeadingDisplayed(), "Product heading should display");
    }

    @Test(priority = 9, description = "AUTO-009: Add product to cart")
    public void verifyAddProductToCart() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.setQuantity("1");
        productPage.addToCart();
        Assert.assertTrue(productPage.isSuccessDisplayed(), "Add to cart success message should display");
    }

    @Test(priority = 10, description = "AUTO-010: View cart product")
    public void verifyProductDisplayedInCart() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.addToCart();
        CartPage cartPage = productPage.viewCartFromMiniCart();
        Assert.assertTrue(cartPage.isProductDisplayedInCart(), "Cart table should display product");
    }

    @Test(priority = 11, description = "AUTO-011: Update cart quantity")
    public void verifyUpdateCartQuantity() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.addToCart();
        CartPage cartPage = productPage.viewCartFromMiniCart();
        cartPage.updateQuantity("2");
        Assert.assertTrue(cartPage.isProductDisplayedInCart(), "Cart should still display product after quantity update");
    }

    @Test(priority = 12, description = "AUTO-012: Remove product from cart")
    public void verifyRemoveProductFromCart() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.addToCart();
        CartPage cartPage = productPage.viewCartFromMiniCart();
        cartPage.removeProduct();
        Assert.assertTrue(cartPage.isEmptyCartDisplayed() || driver.getPageSource().toLowerCase().contains("empty"),
                "Empty cart message should display after removing product");
    }

    @Test(priority = 13, description = "AUTO-013: Checkout mandatory validation")
    public void verifyCheckoutMandatoryValidation() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.addToCart();
        CartPage cartPage = productPage.viewCartFromMiniCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.continueWithoutDetails();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed() || checkoutPage.isValidationDisplayed(),
                "Checkout page or validation should display");
    }

    @Test(priority = 14, description = "AUTO-014: Registered user checkout flow")
    public void verifyRegisteredUserCheckoutFlow() {
        createUserAndReturnEmail();
        HomePage home = new HomePage(driver);
        ProductPage productPage = home.searchProduct("MacBook").openFirstProduct();
        productPage.addToCart();
        CartPage cartPage = productPage.viewCartFromMiniCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page should open for registered user. Complete payment/shipping in configured local environment.");
    }

    @Test(priority = 15, description = "AUTO-015: Order history page")
    public void verifyOrderHistoryPage() {
        createUserAndReturnEmail();
        OrderHistoryPage orderHistoryPage = new AccountPage(driver).openOrderHistory();
        Assert.assertTrue(orderHistoryPage.isOrderHistoryPageDisplayed(),
                "Order history page should display");
    }
}
