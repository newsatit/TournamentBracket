package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;


public class LeaderBoard extends VBox{
    ArrayList<LeaderBlock> leaders;

    public LeaderBoard(int size) {
        this.getStyleClass().add("leaderboard");
        leaders = new ArrayList<>();

        this.setAlignment(Pos.CENTER);

        for (int i = 0; i < size; i++) {
            leaders.add(new LeaderBlock(i + 1));
        }

        Label l = new Label("LeaderBoard");
        l.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.getChildren().add(l);

        VBox blockGroup = new VBox();
        blockGroup.getChildren().addAll(leaders);
        blockGroup.setSpacing(2);
        blockGroup.setId("Leaders");
        this.getChildren().add(new Group(blockGroup));
    }

    public void setLeaders(ArrayList<Challenger> leaders) {
        ObservableList<Node> blockGroup = ((VBox) this.lookup("#Leaders")).getChildren();
        for (int i = 0; i < leaders.size() && i < blockGroup.size(); i++) {
            if (leaders.get(i) != null) ((LeaderBlock) blockGroup.get(i)).setName(leaders.get(i).getName());
        }
    }

    private class LeaderBlock extends HBox{
        private Label rank;
        private Pane LeaderName;

        public LeaderBlock(Integer rank){
            this.setAlignment(Pos.CENTER_LEFT);

            this.rank = new Label(rank.toString() + ".");
            this.rank.setFont(new Font("Arial", 20));

            LeaderName = new Pane();
            LeaderName.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            LeaderName.setPrefSize(180,1);
            LeaderName.getChildren().add(new Label());

            this.getChildren().addAll(this.rank, this.LeaderName);
            this.setLayoutY(10);
        }

        public void setName(String name) {
            ((Label) LeaderName.getChildren().get(0)).setText(name);
        }
    }
}
