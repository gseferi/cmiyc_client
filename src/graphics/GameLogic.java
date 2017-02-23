package graphics;

import java.util.HashMap;

import game.util.Position;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import launcher.Main;
import util.Debug;
import util.Maths;

public class GameLogic {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	private Main client;
	private Pane pane;
	private double mouseX;
	private double mouseY;
	
	public GameLogic(Main client, Pane pane) {

		this.client = client;
		this.pane = pane;
		
		pane.setFocusTraversable(true);
		pane.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
		});
		pane.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
		});
		pane.setOnMouseMoved(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
	}

	public void update() {
		//double angle = Maths.angle(new Position(client.player.position.x, client.player.position.y), new Position(mouseX,mouseY);				
		double angle = getMouseAngle(client.player.position.x, client.player.position.y, mouseX, mouseY);
		client.player.direction = angle;
		
		if (keys.containsKey(KeyCode.W) && keys.get(KeyCode.W)) {
			client.player.position.x += client.player.speed * Math.cos(angle); 
			client.player.position.y += client.player.speed * Math.sin(angle);
		}
		if (keys.containsKey(KeyCode.S) && keys.get(KeyCode.S)) {
			client.player.position.x -= client.player.speed * Math.cos(angle);
			client.player.position.y -= client.player.speed * Math.sin(angle);
		}
		if (keys.containsKey(KeyCode.A) && keys.get(KeyCode.A)) {
			client.player.position.x += client.player.speed * Math.cos(angle - Math.toRadians(90));
			client.player.position.y += client.player.speed * Math.sin(angle - Math.toRadians(90));
		}
		if (keys.containsKey(KeyCode.D) && keys.get(KeyCode.D)) {
			client.player.position.x += client.player.speed * Math.cos(angle + Math.toRadians(90));
			client.player.position.y += client.player.speed * Math.sin(angle + Math.toRadians(90));
		}

	}
	
	public double getMouseAngle(double myX, double myY, double mouseX, double mouseY) {
		if (myX != mouseX && myY != mouseY) {
			double xdif = (mouseX - myX);
			double ydif = (mouseY - myY);
			double angle = 0; // in radians
			angle = -Math.atan(ydif / xdif);
			if (xdif < 0) {
				if (ydif < 0) {
					angle += Math.PI;
				} else {
					angle -= Math.PI;
				}
			}
			return -angle;
		} else if (myX > mouseX) {
			return Math.PI;
		} else if (myX < mouseX) {
			return 0.0;
		} else if (myY > mouseY) {
			return -Math.PI / 2.0;
		} else if (myY < mouseY) {
			return Math.PI / 2.0;
		}
		return 0.0;
	}
}
