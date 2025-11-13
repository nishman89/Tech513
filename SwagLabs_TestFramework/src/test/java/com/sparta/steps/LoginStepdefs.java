package com.sparta.steps;

import com.sparta.pages.*;
import com.sparta.utils.Credentials;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class LoginStepdefs {

    @Managed
    HomePage homePage;

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.open();
    }

    @And("I have entered the username {string}")
    public void iHaveEnteredTheUsername(String username) {
        homePage.enterUserName(username);
    }

    @And("I have entered the password {string}")
    public void iHaveEnteredThePassword(String password) {
        homePage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        homePage.clickLoginButton();
    }

    @Then("I should land on the inventory page")
    public void iShouldLandOnTheInventoryPage() {
        assertThat(homePage.getDriver().getCurrentUrl(), containsString("/inventory.html"));
    }

    @Then("I should see an error message that contains {string}")
    public void iShouldSeeAnErrorMessageThatContains(String expected) {
        assertThat(homePage.getErrorMessage(), containsString(expected));
    }


    @And("I have entered the following credentials:")
    public void iHaveEnteredTheFollowingCredentials(DataTable dataTable) {
        Map<String, String> creds = dataTable.asMap(String.class, String.class);
        Credentials credentials = new Credentials(creds.get("username"), creds.get("password"));
        homePage.enterCredentials(credentials);
    }
}
