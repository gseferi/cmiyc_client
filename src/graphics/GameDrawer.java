package graphics;

import game.Obstacle;
import game.Treasure;
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

        // Draw the obstacles
        for (Obstacle o : main.gameData.obstacles) {
            Rectangle obstacle = new Rectangle(o.topLeft.x, o.topLeft.y,
                    o.width, o.height);
            obstacle.setFill(Color.AQUA);
            pane.getChildren().add(obstacle);
        }
        
        // Draw the treasures
        for (Treasure t : main.gameData.treasures) {
        	Circle treasure = new Circle(t.position.x, t.position.y, 7);
        	treasure.setFill(Color.YELLOW);
        	pane.getChildren().add(treasure);
        }
        
        // Draw the client player
        double x = main.player.position.x;
        double y = main.player.position.y;
        Circle c = new Circle(10, Color.SPRINGGREEN);
        c.setCenterX(x);
        c.setCenterY(y);
        pane.getChildren().add(c);
    }
}
