package application;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Stores and outputs the name of the Challenger and the score. Helps keep track of the challenger's
 * score for a specific match
 * @author
 *
 */
public class ChallengerBlock extends HBox{
	private Challenger challenger;
	private TextField scoreInput;
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
	 * 
	 * @param challenger
	 */
	public void setChallenger(Challenger challenger) {
		this.challenger = challenger;
		this.challengerName.setText(this.challenger.getName());
		this.scoreInput.setEditable(true);
		challengerName.setPrefWidth(150);
	}
	
	/**
	 * 
	 * @return the challenger reference that is stored in this ChallengerBlock
	 */
	public Challenger getChallenger() {
		return challenger;
	}
	
	/**
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
