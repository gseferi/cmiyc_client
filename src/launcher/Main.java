package launcher;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JPanel;

import com.ClientReceiver;
import com.ClientSender;

import audio.AudioMidi;
import constants.Commands.Action;
import constants.Commands.Key;
import game.Faction;
import game.GameData;
import game.GameMode;
import game.Player;
import game.constants.GameSettings;
import gui.ClientLauncher;
import gui.GameScreen;
import gui.OfflineScreen;
import gui.util.ConnectButton;
import gui.util.LauncherButton;
import gui.util.SelecterButton;
import states.ClientState;
import util.Debug;
import util.Transferable;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	public String id;
	public String username;
	public ClientState state;
	
	private int port;
	private String host;
	
	private ClientLauncher gui;
	
	public ObjectInputStream in;
	public ObjectOutputStream out;
	
	public Player player;
	
	public GameData gameData;
	
	private ClientSender sender;
	private ClientReceiver receiver;
	
	private OfflineScreen offlineScreen;
		
	private GameScreen gameScreen;
	
	private ArrayList<Transferable> queue;
	
	public void launcherClick() {
		if( this.gui.launcher.state == LauncherButton.State.START ) { this.gui.launcher.setState( LauncherButton.State.FIND ); this.gui.slider.slideOut(); }
		else { /* find match process */ }
	}

	public void selecterClick( GameMode _mode ) {
		
		this.player.mode = _mode;
		
		if( _mode == GameMode.LONG ) {
			this.gui.longSelecter.state = SelecterButton.State.SELECTED;
			this.gui.shortSelecter.state = SelecterButton.State.DEFAULT;
			this.gui.shortSelecter.repaint();
			this.gui.longSelecter.repaint();
		}
		else {
			this.gui.shortSelecter.state = SelecterButton.State.SELECTED;
			this.gui.longSelecter.state = SelecterButton.State.DEFAULT;
			this.gui.shortSelecter.repaint();
			this.gui.longSelecter.repaint();
		}
		
	}
	
	public void selecterClick( Faction _faction ) {
		
		this.player.faction = _faction;
		
		if( _faction == Faction.SECURITY ) {
			this.gui.securitySelecter.state = SelecterButton.State.SELECTED;
			this.gui.thiefSelecter.state = SelecterButton.State.DEFAULT;
			this.gui.securitySelecter.repaint();
			this.gui.thiefSelecter.repaint();
		}
		else {
			this.gui.thiefSelecter.state = SelecterButton.State.SELECTED;
			this.gui.securitySelecter.state = SelecterButton.State.DEFAULT;
			this.gui.securitySelecter.repaint();
			this.gui.thiefSelecter.repaint();
		}

	}
	
	private void connecterClick( ConnectButton.State _state ) {
		
		if( _state == ConnectButton.State.CONNECT ) { this.connect(); }
		else { this.disconnect(); }
		
	}
		
	private void initOfflineScreen() {
		this.offlineScreen = new OfflineScreen( this );
		offlineScreen.setMinimumSize( new Dimension( GameSettings.Arena.outerSize ) );
		offlineScreen.setPreferredSize( new Dimension( GameSettings.Arena.outerSize ) );
		offlineScreen.setOpaque(false);
		this.gui.offlineWrapper.add(offlineScreen, BorderLayout.CENTER);
		
		this.gui.offlineWrapper.repaint();
		this.gui.offlineWrapper.revalidate();
		this.gui.pack();
	}
	
	private boolean validPort( String _port ) {
		// Check if port is valid;
		return true;
	}
	
	private String[] getInputs() {
		
		String[] splits = this.gui.hostInput.getText().split( ":" );
		
		String[] inputs = new String[3];
		inputs[0] = splits[0];
		inputs[1] = splits[1];
		inputs[2] = this.gui.userInput.getText();
		
		return inputs;
	}
	
	private void useInputs( String[] _inputs ) {
		
		String host = _inputs[0];
		String port = _inputs[1];
		String username = _inputs[2];
		
		this.port = ( this.validPort( port ) ) ? Integer.parseInt( port ) : this.port;
		this.host = host;
		this.username = username;
		
	}
		
	private void connect() {
		
		this.useInputs( this.getInputs() );
		
		Socket socket;
		
		try { socket = new Socket( this.host, this.port ); }
		catch( Exception _exception ) { return; }
		
		try {
			this.out = new ObjectOutputStream( socket.getOutputStream() ); out.flush();
			this.in = new ObjectInputStream( socket.getInputStream() );
		}
		catch( Exception _exception ) {
		
			try { socket.close(); }
			catch( Exception _exception2 ) { }
			return;
			
		}
		
		this.enqueue( new Transferable( Action.UPDATE_USERNAME, new String( this.username ) ) );
		this.startThreads();
	}
	
	private void startThreads() {
		new Thread( this.sender ).start();
		new Thread( this.receiver ).start();
	}
	
	public synchronized void enqueue( Transferable _data ) { this.queue.add( _data ); }
	public synchronized boolean emptyQueue() { return ( this.queue.size() == 0 ); }
	public synchronized Transferable shiftQueue() { Transferable data = this.queue.get( 0 ); this.queue.remove( 0 ); return data; }
	
	public void confirmConnection() {
		this.gui.connecter.setState( ConnectButton.State.DISCONNECT );
		this.gui.connecter.repaint();
		
		this.gui.userInputPanel.setVisible( false );
		this.gui.hostInputPanel.setVisible( false );
		
		
		/*DELETE THIS!!!!!!*/this.initGameScreen(); this.offlineScreen.setVisible( false );this.gui.wrapper.revalidate();this.gui.wrapper.repaint();
	}
	
	public void disconnect() {
		
		this.offlineScreen.setVisible( true );
		this.gui.offlineWrapper.revalidate();
		this.gui.offlineWrapper.repaint();
		this.gui.gameWrapper.remove( this.gameScreen );
		this.gui.gameWrapper.revalidate();
		this.gui.gameWrapper.repaint();

		this.receiver.stopReceiving();		
		this.sender.stopSending();
		
		try { this.in.close(); this.out.close(); } catch( Exception _exception ) { /* Already Closed */ }
		
		this.gui.userInput.setText( "" ); this.gui.userInputPanel.setVisible( true );
		this.gui.hostInput.setText( "" ); this.gui.hostInputPanel.setVisible( true );
		
		this.gui.connecter.setState( ConnectButton.State.CONNECT );
		this.gui.connecter.repaint();
	}
	
	public Main() {
		this.initDefaults();
		this.initGUI();
		this.initOfflineScreen();

	}
	
	private void initDefaults() {
	
		this.gameData = new GameData();
		
		
		this.queue = new ArrayList<Transferable>();
		
		this.sender = new ClientSender( this );
		this.receiver = new ClientReceiver( this );
		
	
		this.id = UUID.randomUUID().toString();
		this.username = "Unknown";
		
		this.port = 1234;
		this.host = "localhost";
		
		this.state = ClientState.DISCONNECTED;
		this.player = new Player( this.id );
	
		this.gui = new ClientLauncher();
		
	
	
	
	}

	private void initGameScreen() {
		this.gameScreen = new GameScreen();
		gameScreen.setMinimumSize( new Dimension( GameSettings.Arena.outerSize ) );
		gameScreen.setPreferredSize( new Dimension( GameSettings.Arena.outerSize ) );
		gameScreen.setOpaque(false);
		this.gui.gameWrapper.add(gameScreen, BorderLayout.CENTER);
		
		this.gui.gameWrapper.revalidate();
		this.gui.gameWrapper.repaint();
	}
	
	private void initGUI() {
		
		this.gui.launcher.addActionListener( event -> this.launcherClick() );
		
		this.gui.shortSelecter.addActionListener( event -> this.selecterClick( GameMode.SHORT ) );
		this.gui.longSelecter.addActionListener( event -> this.selecterClick( GameMode.LONG ) );
		this.gui.securitySelecter.addActionListener( event -> this.selecterClick( Faction.SECURITY ) );
		this.gui.thiefSelecter.addActionListener( event -> this.selecterClick( Faction.THIEF ) );
		
		this.gui.connecter.addActionListener( event -> this.connecterClick( this.gui.connecter.state ) );
		
		this.gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.gui.setVisible( true );
		this.gui.pack();
				
		this.gui.contentPane.setMinimumSize( this.gui.getSize() );
	}

	
	public static void main( String _arguments[] ) {
		new Main();
	}

}
