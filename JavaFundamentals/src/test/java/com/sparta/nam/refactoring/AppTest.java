package com.sparta.nam.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {


//    @Test
//    @DisplayName("My first test")
//    void firstTest(){
//        Assertions.assertTrue(false);
//    }

    @Test
    @DisplayName("getGreeting, when given a time of 21, returns good evening")
    public void getGreeting_GivenATimeOf21_ReturnsGoodEvening(){

        // Arrange
        int time = 21;
        String expected = "Good evening!";
        // Act
        String actual = App.getGreeting(time);
        // Assert
        Assertions.assertEquals(expected, actual);

        Assertions.assertEquals("Good evening!", App.getGreeting(21));
    }


    @Test
    @DisplayName("getGreeting, when given a time of 8, returns good morning")
    public void getGreeting_GivenATimeOf8_ReturnsGoodMorning()
    {
        Assertions.assertEquals("Good morning!", App.getGreeting(8));
    }

    @Test
    @DisplayName("getGreeting, when given a time of 15, returns good afternoon")
    public void getGreeting_GivenATimeOf15_ReturnsGoodAfternoon()
    {
        Assertions.assertEquals("Good afternoon!", App.getGreeting(15));
    }

    @Test
    @DisplayName("getGreeting, when given a time of 2, returns good evening")
    public void getGreeting_GivenATimeOf2_ReturnsGoodEvening()
    {
        Assertions.assertEquals("Good evening!", App.getGreeting(2));
    }

}
