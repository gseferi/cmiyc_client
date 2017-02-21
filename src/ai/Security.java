package ai;

import ai.handler.Handler;
import ai.states.SecurityState;
import game.Faction;
import game.util.Position;

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

			while (!needsUpdate()) {
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

	private void updateListeningPosition() {

	}

	private void updateChasePosition() {
		// TODO Auto-generated method stub

	}

}
