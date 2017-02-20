package graphics;

import javafx.scene.layout.Pane;

public class GameDrawer {

    private Pane pane;

    public GameDrawer() {
        pane = new Pane();
        pane.setStyle("-fx-background-color: black;");
        pane.setPrefSize(800, 600);
    }

    public Pane getPane() {
        return pane;
    }
}
