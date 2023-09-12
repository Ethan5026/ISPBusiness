package edu.iastate.cs228.hw1;
/**
 * @author Ethan Gruening
 * Models an Empty user within a town, based upon the Town Cell type
 */
public class Empty extends TownCell{
    public Empty(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {

        return State.EMPTY;
    }

    @Override
    public TownCell next(Town tNew) {

        TownCell newCell;

        //Checks additional rules
        if(nCensus[EMPTY] + nCensus[OUTAGE] <=1){
            newCell = new Reseller(tNew, row, col);
        }
        //makes new Casual cell, fills it in new grid, and returns
        else{
            newCell = new Casual(tNew, row, col);
        }
        tNew.grid[row][col] = newCell;
        return newCell;
    }

}
