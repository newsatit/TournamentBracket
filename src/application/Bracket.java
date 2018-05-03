/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          A-Team TournamentBracket
// FILES:            Bracket.java, Challenger.java, ChallengerBlock.java, Leaderboard.java, Main.java, Match.java, application.css
//
// USER:             Zhichun Huang, Neal Pongmorrakot, Summer Rawfert, Neal Satitsumpun, Andrew Schaefer
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs
//
//////////////////////////// 80 columns wide //////////////////////////////////
package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * A GridPane that contains the bracket of the tournament.
 * It contains the Match objects which contain the matching of the team in the tournament.
 * @author A-Team 33
 *
 */
public class Bracket extends GridPane {
	
	// the final match of the tournament
	private Match finalMatch;
	// challenger teams of the tournament
	private ArrayList<Challenger> challengers;
	// the leaderboard to display 1,2,3 places
	private LeaderBoard lb;
	
	/**
	 * Constructor of the Bracket 
	 * @param challengers challenger teams in the tournament
	 */
	public Bracket(ArrayList<Challenger> challengers) {
		this.getStyleClass().add("bracket");
	    this.challengers = challengers;
		if(challengers.size() == 0) {//No winner
			this.add(lb = new LeaderBoard(3), 0, 0);
		} else if(challengers.size() == 1) {//instant winner
			this.add(lb = new LeaderBoard(3), 0, 0);
			lb.setLeaders(challengers);
		} else{//2^n challengers
			int currentRow;
			int currentCol;
			if (challengers.size() == 2) {
				currentRow = 1;
				currentCol = 0;
			} else {
				currentRow = (challengers.size() / 2) - 1;
				currentCol = (int) ((Math.log(currentRow) / Math.log(2)) + 1);
			}

			this.add(lb = new LeaderBoard(3), currentCol, 0);
		    finalMatch = createMatches(null, null, currentRow, currentCol, currentRow, challengers);
			finalMatch.setOutputLeaderBoard(lb);
		}
	}
	

	/**
	 * Recursive method that create all the matches and place them in appropriate coordinates by 
	 * splitting the possible candidate teams in the recursive call
	 * @param nextMatch the next match if one of the team in current match will advance to
	 * @param nextBlock the team block if one of the team in current match will advance to
	 * @param space
	 * @param col the x position in the GridePane
	 * @param row the y position in the GridPane
	 * @param challengers the challenger teams that can possibly advance to this match
	 * @return the match with reference to matches in the previous rounds
	 */
	public Match createMatches(Match nextMatch, ChallengerBlock nextBlock,double space, double col, double row, ArrayList<Challenger> challengers) {
	    if(col == 0){
            Match current = new Match(challengers.get(0),challengers.get(1));
            current.setNextBlock(nextBlock);
            current.setNextMatch(nextMatch);
            this.add(new Group(current), (int)col, (int)row);
            return current;
        }else {
	        Match current = new Match();
	        current.setNextBlock(nextBlock);
	        current.setNextMatch(nextMatch);
	        this.add(new Group(current), (int)col, (int)row);
	        // split the ArrayList of team to pass to createMatches
	        ArrayList<Challenger> top = new ArrayList<Challenger>();
	        ArrayList<Challenger> bottom = new ArrayList<Challenger>();
	        top.add(challengers.get(0));
	        boolean addTop = false;
	        for(int i = 1; i< challengers.size() - 2; i+=2) {
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
