package gui;

import constants.Colors;
import game.Camera;
import game.Faction;
import game.Obstacle;
import game.Treasure;
import game.constants.GameSettings;
import gui.util.FxUtils;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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
        pane.setStyle("-fx-background-color: "+ FxUtils.toRGBCode(Colors.black) +";");
        pane.setPrefSize(800, 450);
    }

    
    /**
     * Draws the current state of the game to the Pane.
     */
    public void draw() {
        pane.getChildren().clear();
        drawCameras();
        drawFlashlight();
        drawTreasures();
        drawObstacles();
        drawClient();
    }
    
    private void drawClient() {
    	// Draw the client player
        double x = main.player.position.x;
        double y = main.player.position.y;
        Circle c = new Circle(GameSettings.Player.radius);
        if (main.player.faction == Faction.SECURITY) {
        	c.setFill(Colors.activeSecurity);
        } else {
        	c.setFill(Colors.activeThief);
        }
        c.setCenterX(x);
        c.setCenterY(y);
        pane.getChildren().add(c);
	}

	private void drawObstacles() {
    	// Draw the obstacles
        for (Obstacle o : main.gameData.obstacles) {
        	Rectangle obstacle = new Rectangle(o.topLeft.x, o.topLeft.y,
        			o.width, o.height);
        	pane.getChildren().add(obstacle);
        }

	}

	private void drawFlashlight() {
    	// Draw the flashlight for security
        if (main.player.faction == Faction.SECURITY) {
        	Arc flashlight = new Arc();
        	flashlight.setType(ArcType.ROUND);
        	flashlight.setCenterX(main.player.position.x);
        	flashlight.setCenterY(main.player.position.y);
        	flashlight.setRadiusX(GameSettings.Security.lightRadius);
        	flashlight.setRadiusY(GameSettings.Security.lightRadius);
        	flashlight.setStartAngle(- Math.toDegrees(main.player.direction) - GameSettings.Security.lightRadius/2);
        	flashlight.setLength(GameSettings.Security.lightArcPercentage*360/100);
        	// Sets up light color gradient
        	Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.TRANSPARENT)};
        	RadialGradient lg1 = new RadialGradient(0, 0.1, main.player.position.x, main.player.position.y, GameSettings.Security.lightRadius, false, CycleMethod.NO_CYCLE, stops);
        	flashlight.setFill(lg1);
        	pane.getChildren().addAll(flashlight );
        }
	}

	private void drawTreasures() {
        // Draw the treasures
        for (Treasure t : main.gameData.treasures) {
        	Circle treasure = new Circle(t.position.x, t.position.y, GameSettings.Treasure.radius);
        	treasure.setFill(Colors.treasure);
        	pane.getChildren().add(treasure);
        }
	}

    private void drawCameras() {
    	// Draw the cameras
        for (Camera c : main.gameData.cameras) {
        	Rectangle box = new Rectangle(c.position.x, c.position.y, 10, 10);
        	box.setStroke(Color.GHOSTWHITE);
        	box.setStrokeWidth(4);
        	box.setRotate(- Math.toDegrees(c.direction) - GameSettings.Security.lightRadius/2);
        	Arc camera = new Arc();
        	camera.setType(ArcType.ROUND);
        	camera.setCenterX(c.position.x+5);
        	camera.setCenterY(c.position.y+5);
        	camera.setRadiusX(GameSettings.Security.lightRadius);
        	camera.setRadiusY(GameSettings.Security.lightRadius);
        	camera.setStartAngle(- Math.toDegrees(c.direction) - GameSettings.Security.lightRadius/2);
        	camera.setLength(GameSettings.Security.lightArcPercentage*360/100);
        	Stop[] stops = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.TRANSPARENT)};
        	RadialGradient lg1 = new RadialGradient(0, 0.1, c.position.x, c.position.y, GameSettings.Security.lightRadius, false, CycleMethod.NO_CYCLE, stops);
        	camera.setFill(lg1);
        	pane.getChildren().addAll(camera, box);
        }
    }
}
