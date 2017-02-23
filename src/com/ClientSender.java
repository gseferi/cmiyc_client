package com;

import java.util.HashMap;

import constants.Commands.Action;
import constants.Commands.Key;
import game.Faction;
import launcher.Main;
import util.Debug;
import util.Transferable;

import com.abstracts.AbstractSender;

public class ClientSender extends AbstractSender {

	public ClientSender( Main _client ) { super( _client ); }

	@Override public void run() {
		
		Debug.say( "Sender Started for [ " + this.client.id + " ]" );
		this.running = true;
		
		while( this.running ) {
						
			if( !this.client.emptyQueue() ) { this.send( this.client.shiftQueue() ); }
			if( this.movingOnline() ) { this.send( this.buildMovement() ); }
			
		}

	}

	@Override public void stopSending() { this.running = false; }

	@Override protected Transferable buildMovement() {

		Action action = Action.UPDATE_MOVEMENT;
		HashMap<Key, Object> map = new HashMap<Key, Object>();
		
		map.put( Key.POSITION , this.client.player.position );
		
		if( this.client.player.faction == Faction.SECURITY ) { map.put( Key.DIRECTION, this.client.player.direction ); }
		
		return ( new Transferable( action, map ) );
	
	}

	@Override protected boolean movingOnline() {
		switch( this.client.state ) {
			case FINDING : return true;
			case PREGAME : return true;
			case PLAYING : return true;
			default      : return false;
		}
	}

	@Override protected void send( Transferable _data ) {
	
		if( !this.send( _data, 0 ) ) { this.client.disconnect(); this.running = false; return; }
		
	}

	@Override protected boolean send( Transferable _data, int _triesCount ) {
		
		if( _triesCount >= this.exhaution ) { Debug.say( "Sender Exhausted, preparing to stop." ); return false; }

		Transferable data = _data;
		
		try { this.client.out.reset(); } catch( Exception _exception ) { return this.send( data , ( _triesCount + 1 ) ); }
		try { this.client.out.writeObject( data ); } catch( Exception _exception ) { return this.send( data , ( _triesCount + 1 ) ); }
		try { this.client.out.flush(); } catch( Exception _exception ) { return this.send( data , ( _triesCount + 1 ) ); }

		return true;
		
	}

}
