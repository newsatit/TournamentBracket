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

/**
 * Store information about a team including the name in String and seed that is correspond with it in Integer .
 * @author A-Team 33
 */
public class Challenger {
	private String name;//name of the team
	private Integer seed;//seed of a team; 1 means being the potentially strongest team
	
	/**
	 * Constructor that take assign a name and a seed to the Challenger object
	 * @param name String that contains the name of the team
	 * @param seed Integer that contains the seed of the team with 1 meaning being the 1st seed
	 */
	public Challenger(String name, Integer seed) {
		this.name = name;
		this.seed = seed;
	}
	
	/**
	 * @return name of the team in String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return name of the team in String
	 */
	public Integer getSeed() {
		return seed;
	}
	
}
