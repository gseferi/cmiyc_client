package com.abstracts;

import launcher.Main;
import util.Transferable;

public abstract class AbstractSender implements Runnable {

	protected Main client;																// Thread opener
	protected boolean running = false;													// Whether the Sender should loop or not, SHOULD NOT BE CHANGED FROM WITHIN THIS CLASS!
	protected int exhaution = 10;														// Number of tries this thread should insist on sending data before quitting and disconnecting
	
	public AbstractSender( Main _client ) { this.client = _client; }					// Constructor initialises this Thread Caller
	
	public abstract void run();															// Loop starter
	public abstract void stopSending();													// Stops this thread for looping, making it possible for it to terminate
	
	protected abstract Transferable buildMovement();									// Returns a Transferable object containing information necessary for Server to update movement
	protected abstract boolean movingOnline();											// Checks whether this Sender should send movement to the server or not
	protected abstract void send( Transferable _data );									// Starts trying to send 1 piece of data
	protected abstract boolean send( Transferable _data, int _triesCount );				// Tries sending data recursively for "exhaution" number of times after which it disconnects the client
	
}
