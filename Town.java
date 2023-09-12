package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Ethan Gruening
 *Object defining the town, filled with town cells (users) that can use power and change over time.
 */
public class Town {

	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * The length of the town
	 * @param width
	 * The width of the town
	 */
	public Town(int length, int width) {

		//assign variables
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];


	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * The file name for the template of the grid
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
			FileNotFoundException e = new FileNotFoundException();
			//open the file for scanning
			File f = new File(inputFileName);
			Scanner scnr = null;
			scnr = new Scanner(f);
			if (!f.exists()) {
				throw e;
			}

			//scan in the variables
			length = scnr.nextInt();
			width = scnr.nextInt();
			grid = new TownCell[length][width];

			//begin filling the grid
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < width; j++) {

					//create the new cell
					TownCell newCell = null;

					char c = scnr.next().charAt(0);

					//scans in next word, converts to character, and makes a new cell
					if (c == 'C') {
						newCell = new Casual(this, i, j);
					} else if (c == 'S') {
						newCell = new Streamer(this, i, j);
					} else if (c == 'R') {
						newCell = new Reseller(this, i, j);
					} else if (c == 'E') {
						newCell = new Empty(this, i, j);
					} else if (c == 'O') {
						newCell = new Outage(this, i, j);
					}
					//adds cell to the grid
					grid[i][j] = newCell;
				}
			}
			//close scanner
			scnr.close();

	}

	/**
	 * Returns width of the grid.
	 * @return
	 * Width of the grid
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * @return
	 * Length of the grid
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 * @param seed
	 * The randomized seed used to fill the grid
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {

				//make a cell and randomly assign it to a different type
				TownCell newCell = null;

				int randomInt = rand.nextInt(5);
				if(randomInt == 0) {
					newCell = new Casual(this, i, j);
				} else if(randomInt == 1){
					newCell = new Streamer(this, i, j);
				} else if(randomInt == 2){
					newCell = new Reseller(this, i, j);} else if(randomInt == 3) {
					newCell = new Empty(this, i, j);
				} else if(randomInt ==4) {
					newCell = new Outage(this, i, j);
				}
				//Add it to the grid
				grid[i][j] = newCell;

			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between
	 * the rows.
	 * @return
	 * Returns the town represented in a string
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {

				//Add a space if its not the first in the row, and if it is it will make a new line
				if (j != 0) {s += ' ';} else if(i != 0){s += '\n';}

				//Add the letter determined by the cell type

				if(State.CASUAL == grid[i][j].who()){ s += 'C';} else if(State.EMPTY == grid[i][j].who()){s += 'E';} else if(State.OUTAGE == grid[i][j].who()){s += 'O';} else if(State.STREAMER == grid[i][j].who()){s += 'S';} else if(State.RESELLER == grid[i][j].who()){s += 'R';}

			}
		}
		return s;
	}

}
