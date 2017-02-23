package graphics;

import java.util.HashMap;

import game.GameData;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import launcher.Main;
import util.Debug;

public class GameLogic {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	private Main client;
	private Pane pane;

	public GameLogic(Main client, Pane pane) {

		this.client = client;
		this.pane = pane;
		
		pane.setFocusTraversable(true);
		pane.setOnKeyPressed(e -> {
			Debug.say("here");
			keys.put(e.getCode(), true);
		});
		pane.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
		});
	}

	public void update() {
		if (keys.containsKey(KeyCode.W) && keys.get(KeyCode.W)) {
			Debug.say("imhere now");
			client.player.position.y -= client.player.speed;
		}
		if (keys.containsKey(KeyCode.S) && keys.get(KeyCode.S)) {

		}
		if (keys.containsKey(KeyCode.A) && keys.get(KeyCode.A)) {

		}
		if (keys.containsKey(KeyCode.D) && keys.get(KeyCode.D)) {

		}
	}

}
