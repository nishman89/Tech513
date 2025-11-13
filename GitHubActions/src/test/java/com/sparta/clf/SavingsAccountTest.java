package com.sparta.clf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SavingsAccountTest {
    @Test
    public void testAddInterest() {
        SavingsAccount myAccount = new SavingsAccount(1000, 5);
        myAccount.addInterest();
        assertEquals(1055, myAccount.getBalance(), 0.001);
    }
}

