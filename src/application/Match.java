package application;

import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Consists of two ChallengerBlocks, and a reference to the next ChallengerBlock the winner of this
 * current match will move to
 * @author
 */
public class Match extends GridPane{
	private ChallengerBlock cb1;
	private ChallengerBlock cb2;
	private Button submitButton;
	private Match nextMatch;
	private ChallengerBlock nextBlock;
    private Match leftPreviousMatch;
    private Match rightPreviousMatch;
	
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
		submitButton = new Button("submit");
		if(c1 == null && c2 == null) {
			cb1 = new ChallengerBlock();
			cb2 = new ChallengerBlock();
			submitButton.setDisable(true);
		} else {
			cb1 = new ChallengerBlock(c1);
			cb2 = new ChallengerBlock(c2); 			
		}
		this.add(cb1, 0, 0);
		this.add(cb2, 0, 1);
		this.add(submitButton, 1, 0, 1, 2);
		GridPane.setValignment(submitButton, VPos.CENTER);		
	}
	/**
	 *  @return Returns the Challenger with the higher score in the current match. If the two challengers
	 *  have the same score, the Challenger with the higher seed (rank) will be chosen as the winner.
	 */ 
	public Challenger getWinner() {
	           
       // If the scores for the two teams are not available, or the score was not inputted correctly,
       // then return null
       if (hasTwoChallengers()) { 
           if (cb1.getScore() == -1 || cb2.getScore() == -1)
              return null;
           
           if (cb1.getScore()==cb2.getScore()) { 
              if ( cb1.getChallenger().getSeed() < cb2.getChallenger().getSeed() )
                   return cb1.getChallenger();
               else
                   return cb2.getChallenger();
           }
           if (cb1.getScore() > cb2.getScore())
               return cb1.getChallenger();
           else
               return cb2.getChallenger();
       }
       return null;  // return null if two challengers have not been initialized yet
	}
		
	/**
	 * 
	 */
	public void handleSubmit() {
		//TODO: implement
	}
	
	
	/**
	 * 
	 * @param nextMatch
	 */
	public void setNextMatch(Match nextMatch) {
		this.nextMatch = nextMatch;
	}
	
	
	/**
	 * 
	 * @param leftPreviousMatch
	 */
	public void setLeftPreviousMatch(Match leftPreviousMatch) {
        this.leftPreviousMatch = leftPreviousMatch;
    }
	
	
	/**
	 * @param rightPreviousMatch
	 */
	public void setRightPreviousMatch(Match rightPreviousMatch) {
        this.rightPreviousMatch = rightPreviousMatch;
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
}
