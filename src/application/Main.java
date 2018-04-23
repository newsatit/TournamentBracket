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
		    
		    ArrayList<Challenger> nameList = readFile("input/teams2.txt");
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
            //System.out.println(file);
            int i = 1;
            while (sc.hasNextLine()) {
                String duh = sc.nextLine().trim();
                if(!duh.equals("")) {
                	nameList.add(new Challenger(duh, i));
                	i++;               	
                }

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nameList;
	}
	
    private ArrayList<Challenger> sortTeam(ArrayList<Challenger> nameList) {
        @SuppressWarnings("unchecked")
        ArrayList<Challenger> sortedList = (ArrayList<Challenger>) nameList.clone();
        int current = 0;
        int i = 0;
        int j = nameList.size() - 1;
        int k = nameList.size() / 2;
//        while(current < nameList.size()) {   
//            //System.out.println("current: " + current + " i: " + i + " j: " + j);
//            //System.out.println(nameList.get(current).getName());
//            //System.out.println(sortedList.get(current).getName());       
//            sortedList.set(i, nameList.get(current));
//            i++;
//            current++;
//            //System.out.println(nameList.get(current).getName());
//            //System.out.println(sortedList.get(current).getName());       
//            sortedList.set(j, nameList.get(current));
//            j--;
//            current++;
//        }
        
        return sortedList;
    }
}
