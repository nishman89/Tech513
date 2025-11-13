package com.sparta.clf;// import the necessary packages
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
// junit tests for the BankAccount class

public class BankAccountTest {
    @Test
    public void testDeposit() {
        BankAccount myAccount = new BankAccount(1000);
        myAccount.deposit(500);
        assertEquals(1500, myAccount.getBalance(), 0.001);
    }

    @Test
    public void testWithdraw() {
        BankAccount myAccount = new BankAccount(1000);
        myAccount.withdraw(200);
        assertEquals(800, myAccount.getBalance(), 0.001);
    }

}
