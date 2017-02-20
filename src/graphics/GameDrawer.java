package graphics;

import game.GameData;

import javafx.scene.layout.Pane;

public class GameDrawer {

    private GameData game;
    private Pane pane;

    public GameDrawer(GameData game) {
        this.game = game;
        pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        pane.setPrefSize(800, 600);
    }

    public Pane getPane() {
        return pane;
    }
}
