package application;

import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
	
	public Match(Challenger c1, Challenger c2) {

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
	
	public Challenger getWinner() {
		// TODO: implement
		return null;
	}
	
	public void handleSubmit() {
		//TODO: implement
	}
	
	public void setNextMatch(Match nextMatch) {
		this.nextMatch = nextMatch;
	}
	
	public void setLeftPreviousMatch(Match leftPreviousMatch) {
        this.leftPreviousMatch = leftPreviousMatch;
    }
	
	public void setRightPreviousMatch(Match rightPreviousMatch) {
        this.rightPreviousMatch = rightPreviousMatch;
    }
	
	public void setNextBlock(ChallengerBlock nextBlock) {
		this.nextBlock = nextBlock;
	}
	
	public boolean hasTwoChallengers() {
		return cb1.getChallenger() != null && cb1.getChallenger() != null;
	}
	
	public ChallengerBlock getNextBlock() {
	    // need implementation
	    return null;
	}
	
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
