package com.sparta.nam.gherkin;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

public class CalculatorStepdefs {
    private Calculator calculator;
    private Integer actual;
    private final ArrayList<Integer> myList = new ArrayList<>();
    private Exception exception;

    @Given("I have a calculator")
    public void iHaveACalculator() { calculator = new Calculator(); }

    @And("I enter {int} and {int} into the calculator")
    public void iEnterAndIntoTheCalculator(int num1, int num2) {
        calculator.setNum1(num1);
        calculator.setNum2(num2);
    }

    @When("I press add")       public void iPressAdd()       { actual = calculator.add(); }
    @When("I press subtract")  public void iPressSubtract()  { actual = calculator.subtract(); }
    @When("I press multiply")  public void iPressMultiply()  { actual = calculator.multiply(); }

    @When("I press divide")
    public void iPressDivide() {
        try { actual = calculator.divide(); }
        catch (ArithmeticException ex) { exception = ex; }
    }

    @And("I enter the numbers below to a list")
    public void iEnterTheNumbersBelowToAList(DataTable table) {
        for (List<String> row : table.asLists()) {
            myList.add(Integer.parseInt(row.get(0).trim()));
        }
    }

    @When("I iterate through the list to add all the even numbers")
    public void iIterateThroughTheListToAddAllTheEvenNumbers() {
        actual = calculator.sumOfEvenNumbers(myList);
    }

    @Then("a ArithmeticException should be thrown")
    public void aArithmeticExceptionShouldBeThrown() {
        Assertions.assertTrue(exception instanceof ArithmeticException);
    }

    @And("the exception should have the message {string}")
    public void theExceptionShouldHaveTheMessage(String expected) {
        Assertions.assertEquals(expected, exception.getMessage());
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int expected) {
        Assertions.assertEquals(expected, actual);
    }
}