package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Streamer class functions with JUnit 5
 */
public class StreamerTest {

    @Test
    @DisplayName("Must return correct Cell Type")
    void who() {
        Streamer streamer = new Streamer(new Town(2, 4), 1, 1);
        assertEquals(State.STREAMER, streamer.who(),"Wrong type returned");
    }

    @Test
    @DisplayName("Must return the correct next cell type starting as Casual based on its neighbors")
    void next() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        town.grid[1][1].census(town.grid[1][1].nCensus);
        Streamer streamer = new Streamer(new Town(4, 4), 1, 1);
        Town newTown = new Town(4,4);
        newTown.randomInit(10);
        streamer.next(newTown);
        assertEquals(State.OUTAGE, newTown.grid[1][1].who(), "Streamer cell does not convert to OUTAGE");
    }
}
