package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class Bracket extends GridPane {
	private ArrayList<Match> matches;
	private Match finalMatch;
	private Match semiMatch1;
	private Match semiMatch2;
	private ArrayList<Challenger> challengers;
	private int index = 0;
	
	// For Testing, delete this after finish
//	public Bracket() {
//		this.add(new Match(), 0, 0);
//		this.add(new Match(), 1, 1);
//		this.add(new Match(), 0, 2);
//	}
	
	public Bracket(ArrayList<Challenger> challengers) {
	    this.challengers = challengers;
		if(challengers.size() == 0) {//No winner
			
		} else if(challengers.size() == 1) {//instant winner
			
		} else {//2^n challengers 
		    double currentRow = (challengers.size()/2) - 1;
		    double currentCol = (int)((Math.log(currentRow)/Math.log(2)) + 1);
		    matches = new ArrayList<Match>();
		    finalMatch = createMatches(null, null, currentRow, currentCol, currentRow);
		}
	}
	
	/*recursive method that create all the matches and store the reference to all matches in the first round into 
	 * the arrayList matches
	 * Known issues: don't have a reference to the semifinal matches
	 */
	public Match createMatches(Match nextMatch, ChallengerBlock nextBlock,double space, double col, double row) {
	    //System.out.println("Space : " + space + " Col: " + col + " Row : " + row);
	    if(col == 0){
            Match current = new Match(challengers.get(index),challengers.get(index+1));
            current.setNextBlock(nextBlock);
            current.setNextMatch(nextMatch);
            this.add(current, (int)col, (int)row);
            matches.add(current);
            //System.out.println(index);
            index = index + 2;//not sure if the use of this is a good object-oriented design
            
            return current;
        }else {
	        Match current = new Match();
	        current.setNextBlock(nextBlock);
	        current.setNextMatch(nextMatch);
	        this.add(current, (int)col, (int)row);
	        //create left previous match
	        current.setLeftPreviousMatch(createMatches(current,current.getCurrentBlock(1),Math.ceil(space/2), col - 1, (row - Math.ceil(space/2))));
	        //create right previous match
	        current.setRightPreviousMatch(createMatches(current,current.getCurrentBlock(1),Math.ceil(space/2), col - 1, (row + Math.ceil(space/2))));
	        return current;
	    }
	}
}
