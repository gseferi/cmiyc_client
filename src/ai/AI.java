package ai;

import java.util.UUID;

import ai.handler.Handler;
import game.Player;

public abstract class AI extends Player implements Runnable {

	private Handler handler;
	private boolean running;
	private boolean update = false;

	public AI(Handler handler) {
		super(UUID.randomUUID().toString());
		this.setHandler(handler);
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

	public boolean needsUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
