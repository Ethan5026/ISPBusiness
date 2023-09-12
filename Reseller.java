package isp;

/**
 * @author Ethan Gruening
 * Models a Reseller user within a town, based upon the Town Cell type
 */
public class Reseller extends TownCell{
    public Reseller(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {

        return State.RESELLER;
    }

    @Override
    public TownCell next(Town tNew) {

        //Makes next Cell
        TownCell newCell;

        //checks if it has 3 or less casuals or empties as a neighbor and causes an outage
        if((nCensus[CASUAL] <= 3) || (nCensus[EMPTY] <= 3)){
            newCell = new Empty(tNew, row, col);
        }

        //Checks additional rules
        else if(nCensus[CASUAL] >=5){
            newCell = new Streamer(tNew, row, col);
        }

        //Stays a reseller
        else{
            newCell = new Reseller(tNew, row, col);
        }

        //Updates new town's grid
        tNew.grid[row][col] = newCell;

        //returns the new cell
        return newCell;


    }
}
