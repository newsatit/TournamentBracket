package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;

public class Bracket extends GridPane {
	private ArrayList<Match> matches;
	private Match finalMatch;
	private Match semiMatch1;
	private Match semiMatch2;
	
	// For Testing, delete this after finish
	public Bracket() {
		this.add(new Match(), 0, 0);
		this.add(new Match(), 1, 1);
		this.add(new Match(), 0, 2);
	}
	
	public Bracket(ArrayList<Challenger> challengers) {
		//TODO: implement
		if(challengers.size() == 0) {
			
		} else if(challengers.size() == 1) {
			
		} else {
			
		}
	}
	
	public void createMatches(Match nextMatch, ChallengerBlock nextBlock, int row, int col) {
		//TODO: implement
	}
	
	
}
