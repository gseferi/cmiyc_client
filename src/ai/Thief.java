package ai;

import ai.handler.Handler;
import ai.states.ThiefState;
import game.Faction;

public class Thief extends AI {

	private ThiefState state;

	public Thief(Handler handler) {
		super(handler);
		this.faction = Faction.THIEF;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		while (isRunning()) {

		}

	}

	@Override
	protected void updateState() {

	}

	public ThiefState getState() {
		return state;
	}

	public void setState(ThiefState state) {
		this.state = state;
	}

}
