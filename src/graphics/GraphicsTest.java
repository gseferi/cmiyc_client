package graphics;

import game.GameData;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        GameData game = new GameData();

        GameDrawer gd = new GameDrawer(game);
        Scene scene = new Scene(gd.getPane());
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Graphics test");
        stage.show();
    }
}
