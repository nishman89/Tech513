package com.sparta.simpletests;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SwagLabTests{
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriver webDriver;
    private static ChromeDriverService service;

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }
    @BeforeAll
    public static void beforeAll() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }
    @BeforeEach
    public void setup() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }
    @AfterAll
    static void afterAll() {
        service.stop();
    }
    @AfterEach
    public void afterEach() {
        webDriver.quit();
    }
    @Test
    @DisplayName("Check that the webdriver works")
    public void checkWebDriver() {
        webDriver.get(BASE_URL);
        Assertions.assertEquals("https://www.saucedemo.com/", webDriver.getCurrentUrl());
        Assertions.assertEquals("Swag Labs", webDriver.getTitle());
    }

    @Test
    @DisplayName("Given I enter a valid username and password, when I click login, then I should land on the inventory page")
    public void successfulLogin() throws InterruptedException {
        webDriver.get(BASE_URL);

        WebElement userNameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        userNameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        Thread.sleep(500);
        Assertions.assertEquals(webDriver.getCurrentUrl(), BASE_URL + "inventory.html");

    }


    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products")
    public void checkNumberOfProductsOnInventoryPage()  {
        webDriver.get(BASE_URL);

        WebElement userNameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        userNameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));
        int productCount = products.size();
        Assertions.assertEquals(productCount, 6);
        
    }

}