package isp;

/**
 * @author Ethan Gruening
 * Models an Outage user within a town, based upon the Town Cell type
 */
public class Outage extends TownCell{
    public Outage(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {

        return State.OUTAGE;
    }

    @Override
    public TownCell next(Town tNew) {
        TownCell newCell;

        //Checks additional rules
        if(nCensus[CASUAL] >=5){
            newCell = new Streamer(tNew, row, col);
        }

        //makes new Empty cell, fills it in new grid, and returns
        else {
            newCell = new Empty(tNew, row, col);
        }
        tNew.grid[row][col] = newCell;
        return newCell;
    }
}
