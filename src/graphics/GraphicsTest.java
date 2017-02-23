package graphics;

import game.GameData;
import game.Player;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GraphicsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        GameData game = new GameData();
        Player p = new Player("D1");
        game.players.put(p.clientID, p);
        p.position.x = 100;
        p.position.y = 160;

        Pane pane = new Pane();
        GameDrawer drawer = new GameDrawer(pane, game);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Graphics test");
        stage.show();

        Thread drawerThread = new Thread(new GameLoop(drawer));
        drawerThread.setDaemon(true);
        drawerThread.start();
    }
}
