package edu.iastate.cs228.hw1;

/**
 * @author Ethan Gruening
 * Models a Casual user within a town, based upon the Town Cell type
 */
public class Casual extends TownCell{
    public Casual(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {

        return State.CASUAL;
    }

    @Override
    public TownCell next(Town tNew) {

        //The next Cell
        TownCell newCell;

        //Checks first additional rule
        if(nCensus[EMPTY] + nCensus[OUTAGE] <=1){
            newCell = new Reseller(tNew, row, col);
        }

        //checks if it has a reseller as a neighbor and causes an outage
        else if(nCensus[RESELLER] > 0){
            newCell = new Outage(tNew, row, col);
        }

        //Checks if it has streamer as a neighbor and makes new streamer
        else if (nCensus[STREAMER] > 0){
            newCell = new Streamer(tNew, row, col);
        }

        //Checks additional rules
        else if(nCensus[CASUAL] >=5){
            newCell = new Streamer(tNew, row, col);
        }

        //Stays a Casual user
        else{
            newCell = new Casual(tNew, row, col);
        }

        //Updates new town's grid
        tNew.grid[row][col] = newCell;

        //returns the new cell
        return newCell;
    }
}
