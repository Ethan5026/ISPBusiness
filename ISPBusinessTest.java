package edu.iastate.cs228.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISPBusinessTest {
    @Test
    @DisplayName("Checks if it updates Plain correctly")
     void updatePlain() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        String original = town.toString();
        Town newTown = ISPBusiness.updatePlain(town);
        String test = "R O R O\n" +
                      "E O E C\n" +
                      "E E C E\n" +
                      "E C C C";

        assertEquals(test, newTown.toString(), "Old town:\n" + original + "\nExpected new town: \n" + test + "\nActual: \n" + newTown.toString());


    }

    @Test
    @DisplayName("Checks Profit Validity")
    void getProfit() throws FileNotFoundException {
        Town town = new Town("tester.txt");
        assertEquals(4, ISPBusiness.getProfit(town), "Expected Profit of 5, Actual Profit of " + ISPBusiness.getProfit(town));
    }
}
