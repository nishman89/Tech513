package com.sparta.simpletests;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class SwagLabsSerenityTests {

    @Managed
    WebDriver webDriver;

    private static final String BASE_URL = "https://www.saucedemo.com/";

    @Test
    @DisplayName("Check that the webdriver works")
    public void checkWebDriver() {
        webDriver.get(BASE_URL);
        Assertions.assertEquals("https://www.saucedemo.com/", webDriver.getCurrentUrl());
        Assertions.assertEquals("Swag Labs", webDriver.getTitle());
    }
    @Test
    @DisplayName("Successful login with valid credentials should redirect to inventory page")
    public void successfulLoginTest() {
        webDriver.get(BASE_URL);

        WebElement usernameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        assertThat(webDriver.getCurrentUrl(), endsWith("/inventory.html"));
    }

    @Test
    @DisplayName("Login with invalid password should display error message")
    public void unsuccessfulLoginTest() {
        webDriver.get(BASE_URL);

        WebElement usernameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("wrong_password");
        loginButton.click();

        WebElement errorMessage = webDriver.findElement(By.cssSelector("[data-test='error']"));
        assertThat(errorMessage.getText(), containsString("Epic sadface"));
    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products")
    public void checkNumberOfProductsOnInventoryPage(){
        webDriver.get(BASE_URL);
        WebElement usernameField= webDriver.findElement(By.name("user-name"));
        WebElement passwordField= webDriver.findElement(By.name("password"));
        WebElement loginButton= webDriver.findElement(By.id("login-button"));
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));
        int productsCount = products.size();
        assertThat(productsCount , is(6));
    }

    @Test
    @DisplayName("Given I am logged in, when I add a product to the cart, then the cart count should increase")
    public void addProductToCartAndVerifyCount() {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        webDriver.findElement(By.cssSelector(".inventory_item button")).click(); // Add first product
        WebElement cartBadge = webDriver.findElement(By.className("shopping_cart_badge"));
        assertThat(cartBadge.getText(), is("1"));
    }

    @Test
    @DisplayName("Given I have a product in the cart, when I remove it, then the cart count should decrease accordingly")
    public void removeProductFromCart() {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        webDriver.findElement(By.cssSelector(".inventory_item button")).click(); // Add
        webDriver.findElement(By.cssSelector(".inventory_item .btn_secondary")).click(); // Remove
        List<WebElement> badges = webDriver.findElements(By.className("shopping_cart_badge"));
        assertThat(badges.size(), is(0)); // No badge when cart is empty
    }

    @Test
    @DisplayName("Given I am logged in, when I logout, then I should be redirected to the login page")
    public void logoutAndVerifyRedirection() {
        webDriver.get(BASE_URL);
        webDriver.findElement(By.name("user-name")).sendKeys("standard_user");
        webDriver.findElement(By.name("password")).sendKeys("secret_sauce");
        webDriver.findElement(By.id("login-button")).click();
        webDriver.findElement(By.id("react-burger-menu-btn")).click();
        webDriver.findElement(By.id("logout_sidebar_link")).click();
        assertThat(webDriver.getCurrentUrl(), is(BASE_URL));
    }

    @Test
    @DisplayName("Automation Exercise – Accept Cookies If Visible")
    public void automationExercisePopup() {
        webDriver.get("https://automationexercise.com/");

        WebElement popupButton =
                webDriver.findElement(By.cssSelector("button.fc-button.fc-cta-consent.fc-primary-button"));

        if (!popupButton.isDisplayed()) {
            popupButton.click();
            System.out.println("Consent popup appeared – clicked Accept.");
        } else {
            System.out.println("Consent popup not present – continuing normally.");
        }

        // Continue your test...
    }



    }



