package ai;

import java.util.ArrayList;

import game.Player;
import game.Treasure;
import game.util.Position;
import util.Maths;

public class Helper {

	/**
	 * Get the treasure that is closest to the given point
	 * 
	 * @param p
	 *            The position to compare to
	 * @param treasures
	 *            The treasures to select the closest from
	 * @return The closest treasure to the given point
	 */
	public static Treasure getClosestTreasure(Position p, ArrayList<Treasure> treasures) {
		return treasures.stream()
				.min((t1, t2) -> Double.compare(Maths.dist(p, t1.position), Maths.dist(p, t2.position))).get();

	}

	/**
	 * Get the player that is closest to the given point
	 * 
	 * @param p
	 *            The position to compare to
	 * @param players
	 *            The players to check
	 * @return The closest player
	 */
	public static Player getClosestPlayer(Position p, ArrayList<Player> players) {
		return players.stream().min((p1, p2) -> Double.compare(Maths.dist(p, p1.position), Maths.dist(p, p2.position)))
				.get();

	}

}
