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

/**
 * LeaderBoard - Displays the ranking after the final match
 * @author A-Team 33
 */
public class LeaderBoard extends VBox{
    ArrayList<LeaderBlock> leaders; // list of 1st, 2nd, 3rd place of the challengers

    /**
     * Constructor of the leader board
     * @param size size of the leader board
     */
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

    /**
     * Set the leaders from an arrayList of Challengers
     *
     * The nth challenger in the arrayList corresponds to the nth entry of the LeaderBoard
     * 
     * @param leaders - an ArrayList of Challenger containing the leaders
     */
    public void setLeaders(ArrayList<Challenger> leaders) {
        ObservableList<Node> blockGroup = ((VBox) this.lookup("#Leaders")).getChildren();
        for (int i = 0; i < leaders.size() && i < blockGroup.size(); i++) {
            if (leaders.get(i) != null) ((LeaderBlock) blockGroup.get(i)).setName(leaders.get(i).getName());
        }
    }

    /**
     * LeaderBlock - Displays the ranking and name of one team on the LeaderBoard
     */
    private class LeaderBlock extends HBox{
        private Label rank; // rank of the Challenger
        private Pane LeaderName; // name of the Challenger

        /**
         * Constructor of the LeaderBlock
         * @param rank rank of the Challenger
         */
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

        /**
         * Set Team name of this LeaderBlock
         * @param name - name of the leader
         */
        public void setName(String name) {
            ((Label) LeaderName.getChildren().get(0)).setText(name);
        }
    }
}
