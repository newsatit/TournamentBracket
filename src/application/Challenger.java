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

public class Challenger {
	private String name;
	private Integer seed;
	
	public Challenger(String name, Integer seed) {
		this.name = name;
		this.seed = seed;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getSeed() {
		return seed;
	}
	
}
