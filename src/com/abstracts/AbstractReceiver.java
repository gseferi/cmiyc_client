package com.abstracts;

import java.util.HashMap;

import constants.Commands.Key;
import launcher.Main;
import util.Transferable;

public abstract class AbstractReceiver implements Runnable {

	protected Main client;																// Thread opener
	protected boolean running = false;													// Whether the Receiver should loop or not, SHOULD NOT BE CHANGED FROM WITHIN THIS CLASS!
	protected int exhaution = 10;														// Number of tries this thread should insist on receiving data before quitting and disconnecting

	public AbstractReceiver( Main _client ) { this.client = _client; }					// Constructor initialises Thread Caller
	
	public abstract void run();
	
	public abstract void stopReceiving();												// Stops looping for receiving data from server, making it possible for it to terminate
	protected abstract void processData( Transferable _data );							// Processes the retrieved data from the server
	protected abstract Transferable read();												// Starts trying to read data from the server
	protected abstract Transferable read( int _triesCount );							// Tries to read data from server for "exhaution" number of times after which quits and disconnects
	
	protected abstract void confirmConnection();										// Confirms that this Thread Caller has successfully been registered as a new client on the server
	protected abstract void deployCamera( HashMap<Key, Object> _map );					// Handles when another Security has deployed a camera, therefore the visibility will be available
	protected abstract void updateTreasureState( HashMap<Key, Object> _map );			// Handles Treasure states
	protected abstract void updateGameState( HashMap<Key, Object> _map );				// Updates the game state
	protected abstract void updatePlayerState( HashMap<Key, Object> _map );				// Updates this thread caller's player state
	protected abstract void updateMovement( HashMap<Key, Object> _map );				// Updates players positions and security directions
	
}
