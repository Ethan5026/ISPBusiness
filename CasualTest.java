package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Casual class functions with JUnit 5
 */



public class CasualTest {


    @Test
    @DisplayName("Must return correct Cell Type")
    void who() {
        Casual casual = new Casual(new Town(2, 4), 1, 1);
        assertEquals(State.CASUAL,casual.who(), "Wrong type returned");
    }

    @Test
    @DisplayName("Must return the correct next cell type starting as Casual based on its neighbors")
    void next() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        town.randomInit(10);
        town.grid[0][3].census(town.grid[0][3].nCensus);
        Casual casual = new Casual(new Town(4, 4), 0, 3);
        Town newTown = new Town(4,4);
        newTown.randomInit(10);
        casual.next(newTown);
        assertEquals(State.OUTAGE, newTown.grid[0][3].who(), "Casual cell does not convert to OUTAGE");
    }
}
