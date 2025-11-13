package com.sparta.simpletests;


import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SwagLabTests{
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriver webDriver;
    private static ChromeDriverService service;

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless");
        // options.setImplicitWaitTimeout(Duration.ofSeconds(10));
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

        Wait<WebDriver> webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(BASE_URL);

        WebElement userNameField = webDriver.findElement(By.name("user-name"));
        WebElement passwordField = webDriver.findElement(By.name("password"));
        WebElement loginButton = webDriver.findElement(By.id("login-button"));

        userNameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));
        int productCount = products.size();

        webDriverWait.until(driver -> driver.getCurrentUrl().contains("/inventory"));

        Assertions.assertEquals(productCount, 6);

    }

    @Test
    @DisplayName("Given I enter a valid username and an invalid password, when I click login, then I should see an error message containing 'Epic sadface'")
    public void unsuccessfulLoginTest_InvalidPassword(){
        webDriver.get(BASE_URL);
        WebElement usernameField= webDriver.findElement(By.name("user-name"));
        WebElement passwordField= webDriver.findElement(By.name("password"));
        WebElement loginButton= webDriver.findElement(By.id("login-button"));
        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("invalid");
        loginButton.click();
        WebElement alert = webDriver.findElement(By.className("error-message-container"));
        Assertions.assertTrue(alert.getText().contains("Epic sadface"));
    }

    @Test
    @DisplayName("Web scraping demo")
    public void retrieveProductInfo() throws IOException {
        // Navigate to the sauce labs demo site
        webDriver.get("https://www.saucedemo.com");

        WebElement usernameField = webDriver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce", Keys.ENTER);

        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));

        try (PrintWriter writer = new PrintWriter(new FileWriter("inventoryItems.txt"))) {
            for (WebElement product: products) {
                WebElement nameElement = product.findElement(By.className("inventory_item_name"));
                WebElement priceElement = product.findElement(By.className("inventory_item_price"));
                String productInfo = nameElement.getText() + ": " + priceElement.getText();
                writer.println(productInfo);
                System.out.println(productInfo);
            }
        }
        Assertions.assertEquals(products.size(), 6);
    }

    @Test
    @DisplayName("Given I am on the Drag and Drop page, when I drag Box A to Box B, then the boxes have switched positions")
    public void dragAndDropTests() throws InterruptedException {
        // Set up ChromeDriver

        // Navigate to the page
        webDriver.get("https://demoqa.com/droppable/");

        // Find elements for dragging and dropping
        WebElement columnA = webDriver.findElement(By.id("draggable"));
        WebElement columnB = webDriver.findElement(By.id("droppable"));

        // Perform drag and drop action
        Actions action = new Actions(webDriver);
        action.dragAndDrop(columnA, columnB).perform();

        // Assert that the text of columnB has changed to "Dropped!"
        Assertions.assertEquals(columnB.getText(), "Dropped!");

    }

    @Test
    @DisplayName("Alert example")
    public void alertTest() throws InterruptedException {
        // Navigate to the alerts page on demoqa.com
        webDriver.get("https://demoqa.com/alerts");

        // Find the button that triggers the prompt alert and click it
        WebElement promptButton = webDriver.findElement(By.id("promtButton"));
        promptButton.click();

        // Switch to the alert
        Alert alert = webDriver.switchTo().alert();

        // Assert that the alert's text is as expected
        Assertions.assertEquals(alert.getText(),"Please enter your name");

        // Send the text "Hello" to the alert's input field
        alert.sendKeys("Hello");

        // Accept the alert to close it
        alert.accept();

        // Find the element that displays the result of the prompt alert
        WebElement promptResult = webDriver.findElement(By.id("promptResult"));

        // Assert that the result text contains the string "You entered Hello"
        Assertions.assertEquals(promptResult.getText(), "You entered Hello");
    }

    @Test
    @DisplayName("Automation Exercise – Accept Cookies If Visible")
    public void automationExercisePopup() throws InterruptedException {
        webDriver.get("https://automationexercise.com/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        List<WebElement> consentButtons = webDriver.findElements(By.cssSelector("button.fc-cta-consent"));

        if (!consentButtons.isEmpty() && consentButtons.get(0).isDisplayed()) {
            consentButtons.get(0).click();
            System.out.println("Consent popup appeared – clicked Accept.");

            // Optional: wait until popup disappears
            wait.until(ExpectedConditions.invisibilityOf(consentButtons.get(0)));
        }
        WebElement signupLoginLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login"))
        );
        signupLoginLink.click();
        assertThat(webDriver.getCurrentUrl(), containsString("login"));

    }




}