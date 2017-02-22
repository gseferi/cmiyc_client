package ai;

import ai.handler.Handler;
import ai.states.SecurityState;
import game.Faction;
import game.Player;
import game.util.Position;
import util.Maths;

public class Security extends AI {

	private SecurityState state;

	private double turnSpeedHigh = 1.0;
	private double turnSpeedLow = 0.5;
	private double moveSpeedHigh = 1.0;
	private double moveSpeedLow = 0.5;

	private Position nextWaypoint;

	public Security(Handler handler) {
		super(handler);
		this.faction = Faction.SECURITY;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		if (nextWaypoint == null)
			nextWaypoint = Helper.getClosestTreasure(this.position, this.getHandler().gameData.treasures).position;

		while (isRunning()) {

			// Wait until the AI should be updated
			while (!needsUpdate()) {
				// Prevent thread races
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			switch (this.state) {
			case CHASING:
				updateChasePosition();
				break;
			case LISTENING:
				updateListeningPosition();
				break;
			case MOVING:
				updateMovingPosition();
				break;
			case SCANNING:
				updateScanningPosition();
				break;
			default:
				break;

			}

			setUpdate(false);

		}

	}

	private void updateScanningPosition() {
		// TODO Auto-generated method stub

	}

	private void updateMovingPosition() {
		// TODO Auto-generated method stub

	}

	/**
	 * Update the position of the AI based on the fact that it can hear a villain
	 */
	private void updateListeningPosition() {
		double leftVol = Maths.getLeftVolume(this.position, this.getHandler().gameData.players);
		double rightVol = Maths.getLeftVolume(this.position, this.getHandler().gameData.players);
		turnFast(leftVol > rightVol);
		moveSlow();
	}

	private void updateChasePosition() {
		// TODO Auto-generated method stub

	}

	/**
	 * Move the AI forward at the faster speed
	 */
	private void moveFast() {
		this.position = Maths.project(this.position, moveSpeedHigh, this.direction);
	}

	/**
	 * Move the AI forward at the slower speed
	 */
	private void moveSlow() {
		this.position = Maths.project(this.position, moveSpeedLow, this.direction);
	}

	/**
	 * Quickly turn the AI
	 * 
	 * @param left
	 *            Should the AI turn left or right?
	 */
	private void turnFast(boolean left) {
		this.direction += left ? -turnSpeedHigh : turnSpeedHigh;
	}

	/**
	 * Slowlu turn the AI
	 * 
	 * @param left
	 *            Should the AI turn left or right?
	 */
	private void turnSlow(boolean left) {
		this.direction += left ? -turnSpeedLow : turnSpeedLow;
	}

}
