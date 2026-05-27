package com.opencart.utils;

public class Config {
    public static final String BASE_URL = System.getProperty("baseUrl", "https://demo.opencart.com/");
    public static final String PASSWORD = System.getProperty("password", "Password@123");
    public static final String FIRST_NAME = "Test";
    public static final String LAST_NAME = "User";
    public static final String TELEPHONE = "9876543210";

    public static String uniqueEmail() {
        return "opencart_test_" + System.currentTimeMillis() + "@example.com";
    }
}
