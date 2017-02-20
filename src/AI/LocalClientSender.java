package AI;

import com.abstracts.AbstractSender;

import launcher.Main;
import util.Transferable;

public class LocalClientSender extends AbstractSender {

	public LocalClientSender(Main _client) {
		super(_client);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopSending() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Transferable buildMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean movingOnline() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void send(Transferable _data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean send(Transferable _data, int _triesCount) {
		// TODO Auto-generated method stub
		return false;
	}

}
