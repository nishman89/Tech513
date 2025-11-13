package com.sparta.steps;

import com.sparta.pages.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class InventoryStepdefs {
    @Managed
    InventoryPage inventoryPage;
    @When("I land on the inventory page")
    public void iLandOnTheInventoryPage() {
        assertThat(inventoryPage.getDriver().getCurrentUrl(), containsString("/inventory.html"));
    }

    @And("The number of available products is {int}")
    public void theNumberOfAvailableProductsIs(int expected) {
        assertThat(inventoryPage.getInventoryItemCount(), is(expected));

    }

    @And("I add an item to the cart")
    public void iAddAnItemToTheCart() {
        inventoryPage.addFirstItemToCart();

    }

    @Then("The item count increases by one")
    public void theItemCountIncreasesByOne() {
        assertThat(inventoryPage.getCartBadgeText(), is("1"));

    }
}
