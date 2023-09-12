package isp;

/**
 * @author Ethan Gruening
 * Models a Streamer user within a town, based upon the Town Cell type
 */
public class Streamer extends TownCell{
    public Streamer(Town p, int r, int c) {
        super(p, r, c);
    }

    @Override
    public State who() {
        return State.STREAMER;
    }

    @Override
    public TownCell next(Town tNew) {

        //The next Cell
        TownCell newCell;

        //checks first addition rules
        if(nCensus[EMPTY] + nCensus[OUTAGE] <=1){
            newCell = new Reseller(tNew, row, col);
        }
        //checks if it has a reseller as a neighbor and causes an outage
        else if(nCensus[RESELLER] > 0){
            newCell = new Outage(tNew, row, col);
        }

        //Checks if there is an outage nearby
        else if (nCensus[OUTAGE] > 0){
            newCell = new Empty(tNew, row, col);
        }

        //Checks additional rules
        else if(nCensus[CASUAL] >=5){
            newCell = new Streamer(tNew, row, col);
        }

        //Stays a Streamer
        else{
            newCell = new Streamer(tNew, row, col);
        }

        //Updates new town's grid
        tNew.grid[row][col] = newCell;

        //returns the new cell
        return newCell;
    }
}
