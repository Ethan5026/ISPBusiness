package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Ethan Gruening
 * Tests the TownCell class functions with JUnit 5
 */
public class TownCellTest {
    @Test
    @DisplayName("Checks gathering the neighbors from a specific cell")
    void census() throws FileNotFoundException {
        Town p = new Town("tester2.txt");

        //Making it do a census on the row 3, col 3 cell
        p.grid[2][2].census(p.grid[2][2].nCensus);
        int[] censusTest = {2, 1, 1, 1, 3};
        for(int i = 0; i < 5; i++) {

            assertEquals(censusTest[i], p.grid[2][2].nCensus[i], "Census is incorrect for town " +
                    "S E R E R\n" +
                    "E C R S O\n" +
                    "C E O S S\n" +
                    "C S R O R\n" +
                    "S C O O R\n" +
                    "Row 3 Column 3 gives " + p.grid[2][2].nCensus[i] + "at index " + i +
                    " and does not give " + censusTest[i]);
        }

    }
}
