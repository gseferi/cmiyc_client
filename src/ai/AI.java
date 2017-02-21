package ai;

import ai.handler.Handler;
import game.Player;

public abstract class AI extends Player implements Runnable {

	private Handler handler;
	private boolean running;
	
	
	public AI(String _clientID, Handler handler) {
		super(_clientID);
		this.handler = handler;
		setRunning(false);
	}
	
	public void start(){
		Thread t = new Thread(this);
		setRunning(true);
		t.start();
	}
	
	public void end(){
		setRunning(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
