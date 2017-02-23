package graphics;

import java.util.Map;

import game.GameData;
import game.Player;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameDrawer extends Pane {

    private GameData game;
    private Pane pane;

    public GameDrawer(Pane pane, GameData game) {
        this.game = game;
        this.pane = pane;
        pane.setStyle("-fx-background-color: black;");
        pane.setPrefSize(840, 530);
    }

    public void draw() {
        pane.getChildren().clear();

        for (Map.Entry<String, Player> entry : game.players.entrySet()) {
            double y = entry.getValue().position.x;
            double x = entry.getValue().position.y;
            Circle c = new Circle(20, Color.BLUE);
            c.relocate(x, y);
            pane.getChildren().add(c);
        }
    }
}
