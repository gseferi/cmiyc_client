package ai;

import java.util.UUID;

import ai.handler.Handler;
import game.Player;

public abstract class AI extends Player implements Runnable {

	private Handler handler;
	private boolean running;

	public AI(Handler handler) {
		super(UUID.randomUUID().toString());
		this.handler = handler;
		setRunning(false);
	}

	public void start() {
		Thread t = new Thread(this);
		setRunning(true);
		t.start();
	}

	public void end() {
		setRunning(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
