package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Reseller class functions with JUnit 5
 */
public class ResellerTest {

    @Test
    @DisplayName("Must return correct Cell Type")
    void who() {
        Reseller reseller = new Reseller(new Town(2, 4), 1, 1);
        assertEquals(State.RESELLER, reseller.who(), "Wrong type returned");
    }

    @Test
    @DisplayName("Must return the correct next cell type starting as Reseller based on its neighbors")
    void next() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        town.grid[1][2].census(town.grid[1][2].nCensus);
        Reseller reseller= new Reseller(new Town(4, 4), 1, 2);
        Town newTown = new Town(4,4);
        newTown.randomInit(10);
        reseller.next(newTown);
        assertEquals(State.EMPTY, newTown.grid[1][2].who(), "Reseller cell does not convert to EMPTY");
    }
}
