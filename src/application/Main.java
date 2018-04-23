package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ScrollPane root = new ScrollPane();
		    BorderPane content = new BorderPane();

		    LeaderBoard lb = new LeaderBoard(3);
		    content.setTop(lb);
		    
		    ArrayList<Challenger> nameList = readFile("input/teams8.txt");
			Bracket bracket = new Bracket(nameList);
		    content.setCenter(bracket);

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
	
    	public static void main(String[] args) {
		
		launch(args);
	}


	private ArrayList<Challenger> readFile(String file){
	    ArrayList<Challenger> nameList = new ArrayList<Challenger>();
        try {
            Scanner sc = new Scanner(new File(file));
            int i = 1;
            while (sc.hasNextLine()) {
                nameList.add(new Challenger(sc.nextLine().trim(), i));
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nameList;
	}
	
    private ArrayList<Challenger> sortTeam(ArrayList<Challenger> nameList) {
        ArrayList<Challenger> sortedList = new ArrayList<Challenger>();
        sortedList.add(nameList.get(0));
        sortedList.add(nameList.get(1));
        int i = 2;
        int rounds = (int)((Math.log(nameList.size())/Math.log(2)));
        while(i <= rounds) {
            int j = sortedList.size();
            int sum = (int) (Math.pow(2,i) - 1);
            while(j > 0) {
                System.out.println("Round: " + i +" Sum: " + sum +  " j: " + j);
                sortedList.add(j, nameList.get(sum - (sortedList.get(j - 1).getSeed() - 1)));
                j--;
            }
            i++;
        }
        return sortedList;
    }
}
