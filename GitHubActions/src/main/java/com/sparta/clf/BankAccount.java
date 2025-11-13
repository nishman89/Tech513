package com.sparta.clf;

// a class representing a bank account
public class BankAccount {
    private double balance;

    // constructor
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // method to deposit money into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // method to withdraw money from the account
    public void withdraw(double amount) {
        balance -= amount;
    }

    // method to get the current balance of the account
    public double getBalance() {
        return balance;
    }
}
