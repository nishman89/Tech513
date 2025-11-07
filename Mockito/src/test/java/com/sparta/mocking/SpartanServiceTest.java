package com.sparta.mocking;

import com.sparta.mocking.model.Spartan;
import com.sparta.mocking.repository.Repository;
import com.sparta.mocking.repository.SpartanListRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpartanServiceTest {

    private SpartanService sut;
    private Repository mockRepository;

    @BeforeEach
    public void setUp(){
        mockRepository = Mockito.mock(Repository.class);
        // mockRepository is acting like a dummy
        sut = new SpartanService(mockRepository);
    }


    @Test
    @DisplayName("Given a list of two Spartans getNumSpartans returns 2")
    public void givenListOfTwoSpartans_getNumSpartans_Returns2(){
        List<Spartan> spartans = new ArrayList<>();
        spartans.add(Mockito.mock(Spartan.class));
        spartans.add(Mockito.mock(Spartan.class));
        // my stub!
        Mockito.when(mockRepository.getAll()).thenReturn(spartans);
        var result = sut.getNumSpartans();
        assertThat(result, is(2));
    }

    @Test
    @DisplayName("Given a Spartan exists, findSpartan should return it")
    void givenSpartanExists_findSpartan_ReturnsSpartan() {
        Mockito.when(mockRepository.find(Mockito.anyInt()))
                .thenReturn(Mockito.mock(Spartan.class));
        var result = sut.findSpartan(Mockito.anyInt());
        assertThat(result, instanceOf(Spartan.class));
    }

    @Test
    @DisplayName("Given a Spartan doesn't exist, findSpartan should throw a RuntimeException")
    void givenSpartanDoesNotExist_findSpartan_ThrowsRuntimeException() {
        Mockito.when(mockRepository.find(Mockito.anyInt()))
                .thenThrow(new NoSuchElementException());
        Assertions.assertThrows(RuntimeException.class, () -> sut.findSpartan(1));
    }

    @Test
    @DisplayName("Check find is called once")
    public void checkFindIsCalledOnceOnRepository(){
        sut.findSpartan(1);
        Mockito.verify(mockRepository).find(1);
    }

    @Test
    @DisplayName("Test correct parameter passed")
    void testCorrectParameterPassed() {
        Spartan spartan = new Spartan("Nish", "Java", LocalDate.now());
        sut.addSpartan(spartan);
        Mockito.verify(mockRepository).add(spartan);
    }
}
