package com.sparta.nam.gherkin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CalculatorStepDefs {

    private Calculator calculator;
    private Integer actual;


    @Given("I have a calculator")
    public void iHaveACalculator() {
        calculator = new Calculator();
    }
    @And("I enter {int} and {int} into the calculator")
    public void iEnterAndIntoTheCalculator(int num1, int num2) {
        calculator.setNum1(num1);
        calculator.setNum2(num2);
    }

    @When("I press add")
    public void iPressAdd() {
        actual = calculator.add();
    }

    @When("I press subtract")
    public void iPressSubtract() {
        actual = calculator.subtract();
    }
    @Then("the result should be {int}")
    public void theResultShouldBe(int expected) {
        Assertions.assertEquals(expected, actual);
    }



}
