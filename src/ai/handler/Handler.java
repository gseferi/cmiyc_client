package ai.handler;

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
			gameData.players.add(new Security(this));

		for (int i = 0; i < numThieves; i++)
			gameData.players.add(new Thief(this));

	}

	/**
	 * Start all of the AIs
	 */
	public void start() {

		for (Player ai : gameData.players) {
			if (ai instanceof AI) {
				((AI) ai).start();
			}
		}

	}

	/**
	 * End all of the AIs
	 */
	public void end() {

		for (Player ai : gameData.players) {
			if (ai instanceof AI) {
				((AI) ai).end();
			}
		}

	}
}
