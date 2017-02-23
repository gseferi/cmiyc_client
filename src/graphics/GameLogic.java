package graphics;

import java.util.ArrayList;
import java.util.HashMap;

import game.GameData;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class GameLogic {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	// private Main client;
	private GameData client;
	private Pane pane;

	public GameLogic(GameData client, Pane pane) {

		this.client = client;
		this.pane = pane;
		pane.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
		});
		pane.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
		});

	}

	public void update() {
		if (keys.get(KeyCode.W)) {
			
		}
		if (keys.get(KeyCode.A)) {

		}
		if (keys.get(KeyCode.S)) {

		}
		if (keys.get(KeyCode.D)) {

		}
	}

}
