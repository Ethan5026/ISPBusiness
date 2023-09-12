package isp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ethan Gruening
 * Tests the Town functions with JUnit 5
 */
public class TownTest {
    @Test
    @DisplayName("Check for correct town width return")
    void getWidth() {
        Town p = new Town(5,4);
        assertEquals(4, p.getWidth(), "Expected Width 4, Returned: " +p.getWidth());
    }

    @Test
    @DisplayName("Check for correct town length return")
    void getLength() {
        Town p = new Town(5,4);
        assertEquals(5, p.getLength(),"Expected Length 4, Returned: " +p.getLength());
    }

    @Test
    @DisplayName("Tests the randomInit functionality")
    void randomInit() {
        Town p = new Town(5, 4);
        p.randomInit(25);
        boolean tester = true;

        //checks if all spaces are filled with an object
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if(p.grid[i][j] == null){
                    tester = false;
                }
            }

        }
        assertEquals(true, tester, "Not all values in the town's grid was filled with a TownCell");
    }

    @Test
    @DisplayName("Checks the proper string is outputted")
    void ToString() throws FileNotFoundException {
        Town p = new Town("tester2.txt");
        String test = "S E R E R\n" +
                "E C R S O\n" +
                "C E O S S\n" +
                "C S R O R\n" +
                "S C O O R";
        assertEquals(test, p.toString(),"Expect town's string: \n" + test + "\nActual: \n " + p.toString());
    }
}
