package AI;

import java.util.HashMap;

import com.abstracts.AbstractReceiver;

import constants.Commands.Key;
import launcher.Main;
import util.Transferable;

public class LocalClientReceiver extends AbstractReceiver {

	public LocalClientReceiver(Main _client) {
		super(_client);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopReceiving() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processData(Transferable _data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Transferable read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Transferable read(int _triesCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void confirmConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deployCamera(HashMap<Key, Object> _map) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateTreasureState(HashMap<Key, Object> _map) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateGameState(HashMap<Key, Object> _map) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updatePlayerState(HashMap<Key, Object> _map) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateMovement(HashMap<Key, Object> _map) {
		// TODO Auto-generated method stub

	}

}
