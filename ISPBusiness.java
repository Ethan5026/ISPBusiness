package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Ethan Gruening
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());

		//set up to update the new town one cell at a time
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				//checks neighbors
				tOld.grid[i][j].census(tOld.grid[i][j].nCensus);

				//updates and sets new cell type
				tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town object
	 * @return profit for the current state in the town grid
	 */
	public static int getProfit(Town town) {

		//counter for all casual users
		int sum = 0;

		//checks through each space in the grid
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {

				//checks if its profitable and increments sum
				if(town.grid[i][j].who() == State.CASUAL){
					sum++;
				}
			}
		}
		return sum;
	}


	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 *
	 * @param args
	 *
	 */
	public static void main(String []args) throws FileNotFoundException {

		int decision = 0;
		Town town = null;

		//continues to ask until correct response
		int row = 0;
		int col = 0;
		boolean error = false;
		//Displays option of whether to make a grid randomly or from a file
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");

		//sets up scanner and takes in their decision
		Scanner scnr = new Scanner(System.in);
		decision = scnr.nextInt();

		//creates a town object if its random
		if (decision == 2) {
			System.out.println("Provide rows, columns, and seed integer separated by a space: ");
			//takes the input
			row = scnr.nextInt();
			col = scnr.nextInt();
			int seed = scnr.nextInt();

			//creates a new randomized object
			town = new Town(row, col);
			town.randomInit(seed);
			scnr.close();

			}
			//if they select to import a file, will create a town object based on it
		else if (decision == 1) {
			System.out.println("Please enter file path");

			//takes the filepath and creates new town object
			String filepath = scnr.next();
			scnr.close();
			try {
				town = new Town(filepath);
			} catch(FileNotFoundException e){
				System.out.println("File Not Found");
					error = true;
				}
		}
		//Error message
		else {
			System.out.println("Error occurred, try again.");
			error = true;
		}

		//If a file error or incorrect entry hasn't occurred
		if(!error){
			int profit = 0;
			Town newTown = new Town(row, col);

			//start the profit info gathering and update town 12 months
			for (int i = 0; i < 12; i++) {

				profit += getProfit(town);

				//Update the town
				newTown = updatePlain(town);
				town = newTown;
			}

				System.out.printf("Total profit utilization: %.2f", (profit / (town.getLength() * town.getWidth() * 12.0) * 100.0));
				System.out.println('%');
		}

	}
}
