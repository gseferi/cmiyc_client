package ai.handler;

import java.util.Map.Entry;

import ai.AI;
import ai.Security;
import ai.Thief;
import game.GameData;
import game.Player;
import util.Debug;

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

		for (Entry<String, Player> e : gameData.players.entrySet()) {
			if (e.getValue() instanceof AI) {
				((AI) e.getValue()).start();
			}
		}

	}

	/**
	 * End all of the AIs
	 */
	public void end() {

		for (Entry<String, Player> e : gameData.players.entrySet()) {
			if (e.getValue() instanceof AI) {
				((AI) e.getValue()).end();
			}
		}

	}
}
