package edu.iastate.cs228.hw1;

/**
 * 
 * @author Ethan Gruening
 *	The abstract class defining a Town Cell, a singular unit in the Town, representing all types of users.
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;


	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;

	public static final int NUM_CELL_TYPE = 5;

	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}

	/**
	 * Checks all neighborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {

		// zero the counts of all customers
		nCensus[RESELLER] = 0;
		nCensus[EMPTY] = 0;
		nCensus[CASUAL] = 0;
		nCensus[OUTAGE] = 0;
		nCensus[STREAMER] = 0;

		//goes through each neighbor in a 3x3 grid
		for(int i = row - 1; i <= row+1; i++){
			for (int j = col - 1; j <= col+1; j++) {

				//breaks when it evaluates itself, or an out of index
				if(((i == row) & (j == col)) || (i < 0) || (j < 0) || (i >= plain.getLength()) || (j >= plain.getWidth())){
					continue;
				}
				//increments the count of the Census based on the cell's type
				TownCell temp = plain.grid[i][j];

				if(State.CASUAL == temp.who()){ nCensus[CASUAL]++;}

				else if(State.EMPTY == temp.who()){nCensus[EMPTY]++;}

				else if(State.OUTAGE == temp.who()){nCensus[OUTAGE]++;}

				else if(State.STREAMER == temp.who()){nCensus[STREAMER]++;}

				else if(State.RESELLER == temp.who()){nCensus[RESELLER]++;}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 *
	 * @return State of the cell
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 *
	 * @param tNew: town of the next cycle
	 * @return next Town cell
	 */
	public abstract TownCell next(Town tNew);

}
