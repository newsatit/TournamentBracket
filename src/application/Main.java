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
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

/**
 * Main class
 *initialize the program including the data and GUI
 *handle file input
 */
public class Main extends Application {
	
	private static ArrayList<Challenger> nameList;//contains all the teams in Challenger ordered from the strongest to the weakest
	
	@Override
	/**
	 * Create the fundamental GUI structure of the program
	 */
	public void start(Stage primaryStage) {
		try {
			ScrollPane root = new ScrollPane();
		    BorderPane content = new BorderPane();

		    Label title = new Label("Team Bracket");
		    title.getStyleClass().add("title");
		    content.setTop(title);

		    content.getStyleClass().add("page_content");
		    content.setCenter(new Group(new Bracket(nameList)));

		    root.setContent(content);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tournament Bracket");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * main method
	 * get the list of team by calling readFile method with the path to the text file containing all the teams as the parameter
	 * @param args Command Line argument
	 */
    public static void main(String[] args) {
    	try {
    		nameList = readFile(args[0]);
    	} catch (FileNotFoundException fnf) {
    		fnf.printStackTrace();
    	}
		launch(args);
	}

    /**
     * Method that read input file and return an ArrayList that contain all the challengers
     * @param file String that contains the path to the text field containing all the teams
     * @return ArrayList<Challenger> that contains all the teams in the same order as it is in the text file
     */
	private static ArrayList<Challenger> readFile(String file) throws FileNotFoundException {
	    ArrayList<Challenger> nameList = new ArrayList<Challenger>();
        Scanner sc = new Scanner(new File(file));
        int i = 1;
        while (sc.hasNextLine()) {
            String duh = sc.nextLine().trim();
            if(!duh.equals("")) {
            	nameList.add(new Challenger(duh, i));
            	i++;               	
            }
        }
        sc.close();
        return nameList;
	}
}
