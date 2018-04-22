package application;
	
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    Pane root = new Pane();

		    LeaderBoard lb = new LeaderBoard(3);
		    lb.setLayoutX(700);
		    lb.setLayoutY(50);
		    root.getChildren().add(lb);

		    Bracket bracket = new Bracket();
		    bracket.setLayoutX(100);
		    bracket.setLayoutY(50);
		    root.getChildren().add(bracket);

			Scene scene = new Scene(root,1000,1000);
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
}
