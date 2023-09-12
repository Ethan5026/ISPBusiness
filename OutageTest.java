package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Outage class functions with JUnit 5
 */
public class OutageTest {

    @Test
    @DisplayName("Must return correct Cell Type")
    void who() {
        Outage outage = new Outage(new Town(2, 4), 1, 1);
        assertEquals(State.OUTAGE, outage.who(), "Wrong type returned");
    }

    @Test
    @DisplayName("Must return the correct next cell type starting as Outage based on its neighbors")
    void next() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        town.grid[2][1].census(town.grid[2][1].nCensus);
        Outage outage = new Outage(new Town(4, 4), 2, 1);
        Town newTown = new Town(4,4);
        newTown.randomInit(10);
        outage.next(newTown);
        assertEquals(State.EMPTY, newTown.grid[2][1].who(), "Outage cell does not convert to Empty");
    }
}
