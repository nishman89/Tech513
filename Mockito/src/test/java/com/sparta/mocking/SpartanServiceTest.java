package com.sparta.mocking;

import com.sparta.mocking.model.Spartan;
import com.sparta.mocking.repository.Repository;
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

    @Test
    @DisplayName("Given one Spartan created in last 24 hours getSpartansCreatedlast24Hours returns 1")
    void GivenOneSpartanCreatedInLast24Hours_getSpartansCreatedLast24Hours_Returns1() {
        List<Spartan> spartans1WithinLast24 = getSpartanListWith1WithinLast24Hours();
        Mockito.when(mockRepository.getAll())
                .thenReturn(spartans1WithinLast24);
        assertThat(sut.getSpartansCreatedLast24Hours().size(), is(1));
    }

    private List<Spartan> getSpartanListWith1WithinLast24Hours() {
        var mockSpartan1 = Mockito.mock(Spartan.class);
        var mockSpartan2 = Mockito.mock(Spartan.class);
        Mockito.when(mockSpartan1.getStartDate())
                .thenReturn(LocalDate.now());
        Mockito.when(mockSpartan2.getStartDate())
                .thenReturn(LocalDate.now().minusDays(2));
        return List.of(mockSpartan1, mockSpartan2);
    }

    @Test
    @DisplayName("Removing a Spartan that exists returns true")
    void removingSpartanThatExists_returnsTrue() {
        Mockito.when(mockRepository.remove(Mockito.anyInt()))
                .thenReturn(true);
        Assertions.assertTrue(sut.removeSpartan(1));
    }

    @Test
    @DisplayName("Removing a Spartan that doesn't exist returns false")
    void removingSpartanThatDoesNotExist_returnsFalse() {
        Mockito.when(mockRepository.remove(Mockito.anyInt()))
                .thenReturn(false);
        Assertions.assertFalse(sut.removeSpartan(1));
    }
}
