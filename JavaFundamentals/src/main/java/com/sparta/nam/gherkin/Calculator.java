package com.sparta.nam.gherkin;

import java.util.List;

public class Calculator {
    private int num1;
    private int num2;

    public void setNum1(int num1) { this.num1 = num1; }
    public void setNum2(int num2) { this.num2 = num2; }

    public Integer add()       { return num1 + num2; }
    public Integer subtract()  { return num1 - num2; }
    public Integer multiply()  { return num1 * num2; }
    public Integer divide() {
        if (num2 == 0) throw new ArithmeticException("Divisor cannot be zero.");
        return num1 / num2;
    }

    public Integer sumOfEvenNumbers(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                sum += number;
            }
        }
        return sum;
    }
}

