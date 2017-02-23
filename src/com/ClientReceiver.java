package com;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import constants.Commands;
import constants.Commands.Key;
import game.Camera;
import game.states.PlayerState;
import game.states.TreasureState;
import game.util.Position;
import launcher.Main;
import util.Debug;
import util.Transferable;

public class ClientReceiver implements Runnable {
	private Main client;
	private boolean running;
	private final int exhaution = 10;
	
	public ClientReceiver(Main client) {
		this.client = client;
		this.running = false;
	}
	
	public void run() {
		this.running = true;
		
		while( this.running ) { this.processData( this.readFromServer() ); }
		
	}

	public void stopReceiving() { this.running = false; }
	
private void processData( Transferable _data ) {
		
		Transferable data = _data;
		
		switch( data.action ) {
			case KILL_CONNECTION : this.running = false; return;
			// All the rest of the actions...
			case SUCCESSFULL_CONNECTION : this.confirmConnection(); return;
			case UPDATE_PLAYER_STATE : this.updatePlayerState(data.object); return;
			case UPDATE_MOVEMENT : this.updatePlayerPosition(data.object); return;
			case UPDATE_TREASURE_STATE : this.updateTreasureState(data.object); return;
			case DEPLOY_CAMERA : this.deployCamera(data.object); return;
			default: return;
		}
		
	}

private void confirmConnection() {
	this.client.confirmConnection();
}

private void updateTreasureState(HashMap<Key, Object> object) {/*
	int no = -1;
	for(int i=0; i < client.gameData.treasures.size(); i++) {
		if(client.gameData.treasures.get(i).treasureID == (int) object.get(Key.TREASURE)) {
			no = i;
			client.gameData.treasures.get(no).state = (TreasureState) object.get(Key.TREASURE_STATE);
			return;
		}
	}
	Debug.say("Error in UpdatePlayerPosition: No player found");*/
}

private void updatePlayerPosition(HashMap<Key, Object> object) {/*
	int no = -1;
	for(int i=0; i < client.gameData.players.size(); i++) {
		if(client.gameData.players.get(i).clientID == (String) object.get(Key.PLAYER)) {
			no = i;
			client.gameData.players.get(no).position = (Position) object.get(Key.POSITION);
			return;
		}
	}
	Debug.say("Error in UpdatePlayerPosition: No player found");*/
}

private void updatePlayerState(HashMap<Key, Object> object) {/*
	int no = -1;
	for(int i=0; i < client.gameData.players.size(); i++) {
		if(client.gameData.players.get(i).clientID == (String) object.get(Key.PLAYER)) {
			no = i;
			client.gameData.players.get(no).state = (PlayerState) object.get(Key.PLAYER_STATE);
			return;
		}
	}
	Debug.say("Error in UpdatePlayerState: No player found");*/
}

private void deployCamera(HashMap<Key, Object> object) {
	client.gameData.cameras.add((Camera) object.get(Key.CAMERA));
	
}

private synchronized Transferable readFromServer() { return this.readFromServer( 0 ); }
private synchronized Transferable readFromServer( int _tries ) {
	
	if( _tries >= this.exhaution ) {
		
		/* TODO: Debug.say( error on more than 10 tries to read something ) */
				
		return new Transferable( Commands.Action.KILL_CONNECTION );
		
	}
		
	Transferable data = null;
	
	try { data = ( Transferable ) this.client.in.readObject(); }
	catch( Exception _exception ) { this.readFromServer( _tries + 1 ); }
	
	return data;
	
}
	
}