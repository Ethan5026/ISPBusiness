package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Empty class functions with JUnit 5
 */
public class EmptyTest {

    @Test
    @DisplayName("Must return correct Cell Type")
    void who() {
        Empty empty = new Empty(new Town(2, 4), 1, 1);
        assertEquals(State.EMPTY, empty.who(), "Wrong type returned");
    }

    @Test
    @DisplayName("Must return the correct next cell type starting as Empty based on its neighbors")
    void next() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        town.grid[2][2].census(town.grid[2][2].nCensus);
        Empty empty = new Empty(new Town(4, 4), 2, 2);
        Town newTown = new Town(4,4);
        newTown.randomInit(10);
        empty.next(newTown);
        assertEquals(State.CASUAL, newTown.grid[2][2].who(), "Empty cell does not convert to CASUAL");
    }
}
