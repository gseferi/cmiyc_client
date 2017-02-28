package ai;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import ai.handler.Handler;
import ai.states.SecurityState;
import game.Faction;
import game.Player;
import game.constants.GameSettings;
import game.util.Position;
import util.Maths;

public class Security extends AI {

	private SecurityState state;

	private double turnSpeedHigh = 1.2;
	private double turnSpeedMid = 0.9;
	private double turnSpeedLow = 0.5;
	private double moveSpeedHigh = 1.2;
	private double moveSpeedMid = 0.9;
	private double moveSpeedLow = 0.5;

	private Position nextWaypoint;
	private Position previousWaypoint;

	private double leftVol;
	private double rightVol;
	private double chasingAngle;
	private double chasingDistance;

	/**
	 * Create a new security AI
	 * 
	 * @param handler
	 *            The AI handler for this AI
	 */
	public Security(Handler handler) {
		super(handler);
		this.faction = Faction.SECURITY;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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

			updateState();

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

	/* (non-Javadoc)
	 * @see ai.AI#updateState()
	 */
	protected void updateState() {

		// Get the volume for the players
		leftVol = Maths.getLeftVolume(this.position, this.getHandler().gameData.players);
		rightVol = Maths.getLeftVolume(this.position, this.getHandler().gameData.players);

		// Check if we can hear anyone
		if (leftVol > 0 || rightVol > 0)
			setState(SecurityState.LISTENING);

		// Check if there is anyone we can chase
		ConcurrentHashMap<String, Player> excHashMap = super.getHandler().gameData.players;
		excHashMap.remove(super.clientID);
		Player closestPlayer = Helper.getClosestPlayer(this.position, (ArrayList<Player>) excHashMap.values());
		double closestDist = Maths.dist(this.position, closestPlayer.position);
		if (closestDist < GameSettings.Security.lightRadius) {
			double angle = Maths.angle(super.position, closestPlayer.position);
			if (angle >= super.direction - GameSettings.Security.lightArcPercentage * 2 * Math.PI
					&& angle <= super.direction + GameSettings.Security.lightArcPercentage * 2 * Math.PI) {
				setState(SecurityState.CHASING);
				chasingAngle = angle;
				chasingDistance = closestDist;
			}
		}

	}

	/**
	 * Set the state of the AI
	 * 
	 * @param state
	 *            The new state of the AI
	 */
	private void setState(SecurityState state) {
		this.state = state;
	}

	/**
	 * 
	 */
	private void updateScanningPosition() {
		// TODO Auto-generated method stub

	}

	private void updateMovingPosition() {

		this.position = Maths.project(this.position, moveSpeedMid, this.direction);
	}

	/**
	 * Update the position of the AI based on the fact that it can hear a villain
	 */
	private void updateListeningPosition() {
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
