# OpenCart Selenium Java Automation TestNG Project

## What this project covers
This is a practice automation framework for OpenCart e-commerce flows:
registration, login, product search, product details, cart, checkout validation,
order flow, and order history.

## Tools
- Java 17
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Page Object Model

## How to run
1. Install Java JDK 17.
2. Install Maven.
3. Open this folder in IntelliJ IDEA or Eclipse.
4. Update `Config.java` if you use a different OpenCart demo/local URL.
5. Run all tests:

```bash
mvn clean test
```

6. Run headless:

```bash
mvn clean test -Dheadless=true
```

## Important practice note
Public demo sites can reset data or change UI. For stable execution, install OpenCart locally
or use your own test environment. For checkout/order tests, configure products, shipping,
payment, and test account data in your environment.
