package ai.handler;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ai.AI;
import ai.Security;
import ai.Thief;
import game.GameData;
import game.Player;

public class Handler {

	public GameData gameData;

	/**
	 * Create a new AI handler
	 * 
	 * @param gameData
	 *            The game data for the single player game
	 */
	public Handler(GameData gameData) {
		this.gameData = gameData;
	}

	/**
	 * Add the number of security and thief AIs to the game
	 * 
	 * @param numSecurity
	 *            The number of security AIs to be added
	 * @param numThieves
	 *            The number of thief AIs to be added
	 */
	public void addPlayers(int numSecurity, int numThieves) {

		for (int i = 0; i < numSecurity; i++)
			gameData.players.put("Security" + i, new Security(this));

		for (int i = 0; i < numThieves; i++)
			gameData.players.put("Thief" + i, new Thief(this));

	}

	/**
	 * Start all of the AIs
	 */
	public void start() {

		Iterator<Entry<String, Player>> i = gameData.players.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<String, Player> pair = (Map.Entry<String, Player>) i.next();
			if (pair.getValue() instanceof AI) {
				((AI) pair.getValue()).start();
			}
		}

	}

	/**
	 * End all of the AIs
	 */
	public void end() {

		Iterator<Entry<String, Player>> i = gameData.players.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry<String, Player> pair = (Map.Entry<String, Player>) i.next();
			if (pair.getValue() instanceof AI) {
				((AI) pair.getValue()).end();
			}
		}

	}
}
