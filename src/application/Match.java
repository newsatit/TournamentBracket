package application;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Consists of two ChallengerBlocks, and a reference to the next ChallengerBlock the winner of this
 * current match will move to
 * @author
 */
public class Match extends HBox{
    private ChallengerBlock cb1;
    private ChallengerBlock cb2;
	private Button submitButton;
	private Match nextMatch;
	private ChallengerBlock nextBlock;
    private Match leftPreviousMatch;
    private Match rightPreviousMatch;
    private boolean done;
    private LeaderBoard lb;
	
	//create empty future match
	public Match() {
		this(null, null);
	}
	
	/**
	 * Sets up the ChallengerBlocks and submit button in a small GridPane that will be later added
	 * to a larger GridPane in the Bracket class
	 * @param c1
	 * @param c2
	 */
	public Match(Challenger c1, Challenger c2) {
		nextMatch = null;
		done = false;

		// Create Submit Button
		submitButton = new Button("submit");
		submitButton.setOnAction((event) -> {
		    handleSubmit();
		});

		if(c1 == null && c2 == null) {
			cb1 = new ChallengerBlock();
			cb2 = new ChallengerBlock();
			submitButton.setDisable(true);
		} else {
			cb1 = new ChallengerBlock(c1);
			cb2 = new ChallengerBlock(c2); 			
		}

		VBox challengerBlocks = new VBox(cb1, cb2);
		challengerBlocks.setSpacing(1);

		this.getChildren().addAll(challengerBlocks, submitButton);
		this.setAlignment(Pos.CENTER);

		GridPane.setValignment(submitButton, VPos.CENTER);		
	}
	/**
	 *  @return Returns the ChallengerBlock with the higher score in the current match. If the two challengers
	 *  have the same score, the Challenger with the higher seed (rank) will be chosen as the winner.
	 */ 
	public ChallengerBlock getWinner() {
	           
       // If the scores for the two teams are not available, or the score was not inputted correctly,
       // then return null
       if (hasTwoChallengers()) { 
           if (cb1.getScore() < 0 || cb2.getScore() < 0) {
               return null;
           }
           if (cb1.getScore().equals(cb2.getScore())) {
              if ( cb1.getChallenger().getSeed() < cb2.getChallenger().getSeed() )
                   return cb1;
               else
                   return cb2;
           }
           if (cb1.getScore().compareTo(cb2.getScore()) > 0)
               return cb1;
           else
               return cb2;
       }
       return null;  // return null if two challengers have not been initialized yet
	}
	
	 /**
     *  @return Returns the ChallengerBlock with the lower score in the current match. If the two challengers
     *  have the same score, the Challenger with the lower seed (rank) will be chosen as the loser.
     */ 
	public ChallengerBlock getLoser() {
        
	       // If the scores for the two teams are not available, or the score was not inputted correctly,
	       // then return null
	       if (hasTwoChallengers()) { 
	           if (cb1.getScore() < 0 || cb2.getScore() < 0) {
	               return null;
	           }
	           if (cb1.getScore()==cb2.getScore()) { 
	              if ( cb1.getChallenger().getSeed() < cb2.getChallenger().getSeed() )
	                   return cb2;
	               else
	                   return cb1;
	           }
	           if (cb1.getScore() > cb2.getScore())
	               return cb2;
	           else
	               return cb1;
	       }
	       return null;  // return null if two challengers have not been initialized yet
	}
		
	/**
	 * pass the challenger to the next match if there is one and enable the text field and submit button
	 * disable the text field and submit button of the current match
	 * change done to true
	 */
	public void handleSubmit() {
		//TODO: implement
	    if(getWinner() != null && getLoser() != null) {
	        cb1.setDisable(true);
            cb2.setDisable(true);
            submitButton.setDisable(true);
            done = true;
            if(nextMatch != null) {
                this.nextBlock.setChallenger(getWinner().getChallenger());
                this.nextMatch.submitButton.setDisable(false);
            } else if (lb != null){
                // TODO : Handle Corner cases
                ArrayList<Challenger> leaders = new ArrayList<>();
                leaders.add(getWinner().getChallenger());
                leaders.add(getLoser().getChallenger());
                // get the third place challenger by comparing the scores of losers in previous match
                if (getLeftPreviousMatch() != null && getRightPreviousMatch() != null) {
                	ChallengerBlock c1 = getLeftPreviousMatch().getLoser();
                	ChallengerBlock c2 = getRightPreviousMatch().getLoser();
                	leaders.add(c1.getScore().compareTo(c2.getScore()) == 0 ? c1.getChallenger().getSeed().compareTo(c2.getChallenger().getSeed()) < 0 ? c1.getChallenger() : c2.getChallenger()
					: c1.getScore().compareTo(c2.getScore()) > 0 ? c1.getChallenger() : c2.getChallenger());
				}
                lb.setLeaders(leaders);
			}
	    }
	}
	
	
	/**
	 * set the reference to the next match
	 * @param nextMatch
	 */
	public void setNextMatch(Match nextMatch) {
		this.nextMatch = nextMatch;
	}
	
	
	/**
	 * set reference to the left previous match
	 * @param leftPreviousMatch
	 */
	public void setLeftPreviousMatch(Match leftPreviousMatch) {
        this.leftPreviousMatch = leftPreviousMatch;
    }
	
	
	/**
	 * set reference to the right previous match
	 * @param rightPreviousMatch
	 */
	public void setRightPreviousMatch(Match rightPreviousMatch) {
        this.rightPreviousMatch = rightPreviousMatch;
    }
	
	/**
	 * get left previous match
	 * @return LeftPreviousMatch
	 */
	public Match getLeftPreviousMatch() {
        return leftPreviousMatch;
    }
    
    
    /**
     * get right previous match
     * @return RightPreviousMatch
     */
    public Match getRightPreviousMatch() {
        return rightPreviousMatch;
    }
	
	/**
	 * Sets the object of the nextBlock using the ChallengerBlock parameter
	 * @param nextBlock the ChallengerBlock that the winner will move to in the next round
	 */
	public void setNextBlock(ChallengerBlock nextBlock) {
		this.nextBlock = nextBlock;
	}
	
	
	/**
	 * returns true if two challengers are initialized in their respective ChallengerBlocks.
	 * False otherwise
	 * @return true if two challengers are initialized in their respective ChallengerBlocks.
	 * False otherwise
	 */
	public boolean hasTwoChallengers() {
		return cb1.getChallenger() != null && cb1.getChallenger() != null;
	}
	

    /**
     * 
     * @return the ChallengerBlock object reference stored in nextBlock
     */
	public ChallengerBlock getNextBlock() {
	    return nextBlock;
	}
	
	/**
	 * 
	 * @param i, which will be used to pick a ChallengerBlock
	 * @return one of the two ChallengerBlock objects stored in this current Match. Returns null if
	 * the index exceeds 2
	 */
	public ChallengerBlock getCurrentBlock(int i) {
	    if(i == 1) {
	        return cb1;
	    }else if(i == 2) {
	        return cb2;
	    }else {
	        System.out.println("There are only 2 ChallengerBlock: 1, 2");
	        return null;
	    }
	        
	}

	public void setOutputLeaderBoard(LeaderBoard lb) { this.lb = lb; }
	
	/**
	 * return true if a match is finished, otherwise return false
	 * @return done
	 */
	public boolean getStatus() {
	    return done;
	}
}
