package graphics;

import game.Obstacle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import launcher.Main;

/**
 * Draws the state of the game to a Pane.
 */
public class GameDrawer {

    private Main main;
    private Pane pane;

    /**
     * Constructs a new GameDrawer. Default graphics settings are set on the
     * Pane.
     */
    public GameDrawer(Main main, Pane pane) {
        this.pane = pane;
        this.main = main;
        pane.setStyle("-fx-background-color: black;");
        pane.setPrefSize(840, 530);
    }

    /**
     * Draws the current state of the game to the Pane.
     */
    public void draw() {
        pane.getChildren().clear();

        // Draw the client player
        double x = main.player.position.x;
        double y = main.player.position.y;
        Circle c = new Circle(10, Color.BLUE);
        c.relocate(x - 10, y - 10);
        
        for(Obstacle i : main.gameData.obstacles) {
        	Rectangle obstacle = new Rectangle(i.topLeft.x, i.topLeft.y, i.width, i.height);
        	obstacle.setFill(Color.AQUA);
        	pane.getChildren().add(obstacle);
        }
        
        pane.getChildren().add(c);
        
        
        
    }
}
