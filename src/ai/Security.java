package ai;

import ai.handler.Handler;
import ai.states.SecurityState;
import game.Faction;

public class Security extends AI {

	private SecurityState state;

	public Security(Handler handler) {
		super("AISecurity", handler);
		this.faction = Faction.SECURITY;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		while(isRunning()){
			
		}
		
	}

}
