package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.layout.GridPane;

public class Bracket extends GridPane {
	private ArrayList<Match> matches;
	private Match finalMatch;
	private Match semiMatch1;
	private Match semiMatch2;
	private ArrayList<Challenger> challengers;
	private int index = 0;
	
	public Bracket(ArrayList<Challenger> challengers) {
	    this.challengers = challengers;
		if(challengers.size() == 0) {//No winner
			
		} else if(challengers.size() == 1) {//instant winner
			
		} else {//2^n challengers 
		    double currentRow = (challengers.size()/2) - 1;
		    double currentCol = (int)((Math.log(currentRow)/Math.log(2)) + 1);
		    matches = new ArrayList<Match>();
		    finalMatch = createMatches(null, null, currentRow, currentCol, currentRow, challengers);
		}
	}
	
	/*recursive method that create all the matches and store the reference to all matches in the first round into 
	 * the arrayList matches
	 * Known issues: don't have a reference to the semifinal matches
	 */
	public Match createMatches(Match nextMatch, ChallengerBlock nextBlock,double space, double col, double row, ArrayList<Challenger> challengers) {
	    //System.out.println("Space : " + space + " Col: " + col + " Row : " + row);
	    if(col == 0){
            Match current = new Match(challengers.get(0),challengers.get(1));
            current.setNextBlock(nextBlock);
            current.setNextMatch(nextMatch);
            this.add(current, (int)col, (int)row);
            matches.add(current);
            return current;
        }else {
	        Match current = new Match();
	        current.setNextBlock(nextBlock);
	        current.setNextMatch(nextMatch);
	        this.add(current, (int)col, (int)row);
	        // split the ArrayList of team to pass to createMatches
	        ArrayList<Challenger> top = new ArrayList<Challenger>();
	        ArrayList<Challenger> bottom = new ArrayList<Challenger>();
	        top.add(challengers.get(0));
	        boolean addTop = false;
	        for(int i = 1; i< challengers.size() - 2 ; i+=2) {
	        	if(addTop) {
	        		top.add(challengers.get(i));
	        		top.add(challengers.get(i+1));
	        	} else {
	        		bottom.add(challengers.get(i));
	        		bottom.add(challengers.get(i+1));
	        	}
	        	addTop = !addTop;
	        }
	        top.add(challengers.get(challengers.size()-1));
	        //create left previous match
	        current.setLeftPreviousMatch(createMatches(current,current.getCurrentBlock(1),Math.ceil(space/2), col - 1, (row - Math.ceil(space/2)), top));
	        //create right previous match
	        current.setRightPreviousMatch(createMatches(current,current.getCurrentBlock(2),Math.ceil(space/2), col - 1, (row + Math.ceil(space/2)), bottom));
	        return current;
	    }
	}
}
