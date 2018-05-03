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


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Stores and outputs the name of the Challenger and the score. Helps keep track of the challenger's
 * score for a specific match
 * @author A-Team 33
 *
 */
public class ChallengerBlock extends HBox{
	// the challenger that is stored in the block
	private Challenger challenger;
	// the input for entering score
	private TextField scoreInput;
	// the label that display the name of the challenger
	private Label challengerName;
	
	/**
	 * default constructor that sets the Challenger to null
	 */
	public ChallengerBlock() {
		this(null);
	}
	
	
	/**
	 * Sets up the prompt text and the name of the Challenger. If no challenger is present, then the
	 * ChallengerBlock will output text "TBD"
	 * @param challenger that will be outputted in this block
	 */
	public ChallengerBlock(Challenger challenger) {
        this.getStyleClass().add("challenger_block");
		this.challenger = challenger;
		this.scoreInput = new TextField();
		this.scoreInput.setPromptText("Enter Score");
		if(challenger == null) {
			this.challengerName = new Label("TBD");
			this.scoreInput.setEditable(false);
		} else {
			this.challengerName = new Label(this.challenger.getName());
		}
		challengerName.setPrefWidth(150);
		scoreInput.setMaxWidth(100);
		this.getChildren().addAll(this.challengerName, this.scoreInput);
	}
	
	/**
	 * Set the challenger to the block
	 * @param challenger the challenger to be set to the block.
	 */
	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
		this.challengerName.setText(this.challenger.getName());
		this.scoreInput.setEditable(true);
		challengerName.setPrefWidth(150);
	}
	
	/**
	 * Get the challenger that is stored in the block.
	 * @return the challenger reference that is stored in this ChallengerBlock
	 */
	public Challenger getChallenger() {
		return challenger;
	}
	
	/**
	 * Get the score that is input in the textfield.
	 * @return Integer. -1 if scoreInput is not an integer or if getText() is called on an empty String
	 */
	public Integer getScore() {
		Integer score;
		try {
			score = Integer.parseInt(scoreInput.getText().trim());
		} catch(NumberFormatException e) {
			score = -1;
		}
		return score;
	}
}
