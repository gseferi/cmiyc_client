package graphics;

import game.Obstacle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import launcher.Main;

import logic.GameLogic;
import logic.GameLoop;

/**
 * Main class that tests the graphics module.
 */
public class GraphicsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Main main = new Main();

        main.gameData.obstacles.add(new Obstacle(20, 20, 50, 50));
        main.gameData.obstacles.add(new Obstacle(400, 420, 120, 80));
        main.gameData.obstacles.add(new Obstacle(200, 180, 60, 120));
        main.gameData.obstacles.add(new Obstacle(600, 50, 100, 100));

        Pane pane = new Pane();

        GameLogic logic = new GameLogic(main, pane);
        GameDrawer drawer = new GameDrawer(main, pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Graphics test");
        stage.show();

        // requestFocus() only works after stage.show().
        pane.requestFocus();

        Thread drawerThread = new Thread(new GameLoop(drawer, logic));
        drawerThread.setDaemon(true);
        drawerThread.start();
    }
}
