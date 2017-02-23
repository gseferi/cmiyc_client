package graphics;

import game.GameData;
import game.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import launcher.Main;

public class GraphicsTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
    	Main main = new Main();
    	Pane pane = new Pane();
        
        GameLogic logic = new GameLogic(main, pane);
        GameDrawer drawer = new GameDrawer(pane, main.gameData, main);
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setTitle("Graphics test");
        stage.show();
        pane.requestFocus();
        
        Thread drawerThread = new Thread(new GameLoop(drawer, logic));
        drawerThread.setDaemon(true);
        drawerThread.start();
    }
}
