package com.sparta.tests;

import com.sparta.pages.HomePage;
import com.sparta.pages.InventoryPage;
import io.cucumber.java.en.Given;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(SerenityJUnit5Extension.class)
public class SwagLabsSerenityTest_POM {

    @Managed
    HomePage homePage;

    @Managed
    InventoryPage inventoryPage;

    @Test
    @DisplayName("Given I enter a valid username and password, when I click login, then I should land on the inventory page")
    public void successfulLoginTest(){
        homePage.open();
        homePage.enterUserName("standard_user");
        homePage.enterPassword("secret_sauce");
        homePage.clickLoginButton();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", homePage.getDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Given I enter a valid username and invalid password, when I click login, then I should see an error essage")
    public void unsuccessfulLoginTest(){
        homePage.open();
        homePage.enterUserName("standard_user");
        homePage.enterPassword("invalid");
        homePage.clickLoginButton();
        Assertions.assertTrue(homePage.getErrorMessage().contains("Epic sadface"));
    }

    @Test
    @DisplayName("Given I am logged in, when I view the inventory page, I should see the correct number of products")
    public void checkNumberOfProductsOnInventoryPage(){
        homePage.open();
        homePage.enterUserName("standard_user");
        homePage.enterPassword("secret_sauce");
        homePage.clickLoginButton();
        Assertions.assertEquals(6, inventoryPage.getInventoryItemCount());
    }
}
