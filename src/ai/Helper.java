package ai;

import java.util.ArrayList;

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

}
