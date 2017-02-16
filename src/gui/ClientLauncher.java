package gui;

import javax.swing.Box;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import game.constants.GameSettings;
import gui.util.CancelButton;
import gui.util.ConnectButton;
import gui.util.InputPanel;
import gui.util.InvisibleInput;
import gui.util.LauncherButton;
import gui.util.SelecterButton;
import gui.util.Slider;
import util.GUISettings;

import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ClientLauncher extends JFrame {

	public JPanel contentPane;
	
	public Slider slider;
	public JLayeredPane wrapper;
	
	public InputPanel userInputPanel;
	public InputPanel hostInputPanel;
	
	public LauncherButton launcher;
	public ConnectButton connecter;
	public InvisibleInput userInput;
	public InvisibleInput hostInput;
	
	public SelecterButton shortSelecter;
	public SelecterButton longSelecter;
	public SelecterButton securitySelecter;
	public SelecterButton thiefSelecter;
	
	public JPanel offlineWrapper;
	public JPanel gameWrapper;
	
	public ClientLauncher() {
		
		this.contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.setContentPane( contentPane );
				
		this.wrapper = new JLayeredPane();
		getContentPane().add(wrapper, BorderLayout.CENTER);
		GridBagLayout gbl_wrapper = new GridBagLayout();
		gbl_wrapper.columnWidths = new int[]{0};
		gbl_wrapper.rowHeights = new int[]{0};
		gbl_wrapper.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_wrapper.rowWeights = new double[]{Double.MIN_VALUE};
		wrapper.setLayout(gbl_wrapper);
		
		this.gameWrapper = new JPanel();
		gameWrapper.setOpaque(false);
		GridBagConstraints gbc_gameWrapper = new GridBagConstraints();
		gbc_gameWrapper.fill = GridBagConstraints.BOTH;
		gbc_gameWrapper.gridx = 0;
		gbc_gameWrapper.gridy = 0;
		wrapper.setLayer( gameWrapper, 1 );
		wrapper.add(gameWrapper, gbc_gameWrapper);
		gameWrapper.setLayout(new BorderLayout(0, 0));
				
		final Color normal = Colors.normal;
		
		JPanel gscBeautifier = new JPanel() { 
			public void paintComponent( Graphics _graphics ) {
			
				Graphics2D graphics = ( Graphics2D )_graphics;
				
				graphics.setPaint( new Color( 36, 38, 41 ) );
				graphics.fillRect( 0, 0, this.getWidth(), 1 );
				graphics.setPaint( normal );
				graphics.fillRect( 0, 1, this.getWidth(), this.getHeight() - 1 );
				
			}
		};
		gscBeautifier.setMinimumSize( new Dimension( gscBeautifier.getMinimumSize().width, (int) GUISettings.controlsHeight ) );
		gscBeautifier.setMaximumSize( new Dimension( gscBeautifier.getMaximumSize().width, (int) GUISettings.controlsHeight ) );
		gscBeautifier.setPreferredSize( new Dimension( gscBeautifier.getPreferredSize().width, (int) GUISettings.controlsHeight ) );
		gameWrapper.add(gscBeautifier, BorderLayout.SOUTH);

		this.offlineWrapper = new JPanel();
		offlineWrapper.setOpaque(false);
		GridBagConstraints gbc_offlineWrapper = new GridBagConstraints();
		gbc_offlineWrapper.fill = GridBagConstraints.BOTH;
		gbc_offlineWrapper.gridx = 0;
		gbc_offlineWrapper.gridy = 0;
		wrapper.setLayer( offlineWrapper, 2 );
		wrapper.add(offlineWrapper, gbc_offlineWrapper);
		offlineWrapper.setLayout(new BorderLayout(0, 0));
		
		JPanel controlsBeautifier = new JPanel() { 
			public void paintComponent( Graphics _graphics ) {
			
				Graphics2D graphics = ( Graphics2D )_graphics;
				
				graphics.setPaint( new Color( 36, 38, 41 ) );
				graphics.fillRect( 0, 0, this.getWidth(), 1 );
				graphics.setPaint( normal );
				graphics.fillRect( 0, 1, this.getWidth(), this.getHeight() - 1 );
				
			}
		};
		controlsBeautifier.setMinimumSize( new Dimension( controlsBeautifier.getMinimumSize().width, (int) GUISettings.controlsHeight ) );
		controlsBeautifier.setMaximumSize( new Dimension( controlsBeautifier.getMaximumSize().width, (int) GUISettings.controlsHeight ) );
		controlsBeautifier.setPreferredSize( new Dimension( controlsBeautifier.getPreferredSize().width, (int) GUISettings.controlsHeight ) );
		offlineWrapper.add(controlsBeautifier, BorderLayout.SOUTH);

		JPanel sliderWrapper = new JPanel();
		sliderWrapper.setOpaque(false);
		GridBagConstraints gbc_sliderWrapper = new GridBagConstraints();
		gbc_sliderWrapper.fill = GridBagConstraints.BOTH;
		gbc_sliderWrapper.gridx = 0;
		gbc_sliderWrapper.gridy = 0;
		wrapper.setLayer( sliderWrapper, 3 );
		wrapper.add(sliderWrapper, gbc_sliderWrapper);
		sliderWrapper.setLayout(new BorderLayout(0, 0));
		
		this.slider = new Slider( this.wrapper );
		slider.setOpaque(false);
		sliderWrapper.add(slider, BorderLayout.EAST);
		slider.setLayout(new BorderLayout(0, 0));
		
		JPanel sliderContent = new JPanel();
		sliderContent.setOpaque( false );
		sliderContent.setMinimumSize( new Dimension( (int) GUISettings.sliderWidth, sliderContent.getMinimumSize().height ) );
		sliderContent.setMaximumSize( new Dimension( (int) GUISettings.sliderWidth, sliderContent.getMaximumSize().height ) );
		sliderContent.setPreferredSize( new Dimension( (int) GUISettings.sliderWidth, sliderContent.getPreferredSize().height ) );
		slider.add(sliderContent, BorderLayout.WEST);
		sliderContent.setLayout(new BorderLayout(0, 0));
		
		JPanel connectArea = new JPanel() {
			
			public void paintComponent( Graphics _graphics ) {
				
				Graphics2D graphics = ( Graphics2D )_graphics;

				graphics.setPaint( Colors.darkest );
				graphics.fillRect( 0, 0, this.getWidth(), this.getHeight() );
				graphics.setPaint( new Color( 37, 39, 42 ) );
				graphics.fillRect( this.getHeight() - 1, 0, this.getWidth(), 1 );

			}
			
		};
		connectArea.setBorder( new EmptyBorder( 5, 0, 10, 0 ) );
		sliderContent.add(connectArea, BorderLayout.NORTH);
		connectArea.setLayout(new BoxLayout(connectArea, BoxLayout.Y_AXIS));
		
		this.userInputPanel = new InputPanel();
		connectArea.add(userInputPanel);
		userInputPanel.setLayout(new BorderLayout(0, 0));
		
		userInput = new InvisibleInput( "Username..." );
		userInputPanel.add(userInput, BorderLayout.CENTER);
		userInput.setColumns(10);
		
		connectArea.add( Box.createVerticalStrut( 2 ) );
		
		this.hostInputPanel = new InputPanel();
		connectArea.add(hostInputPanel);
		hostInputPanel.setLayout(new BorderLayout(0, 0));
		
		hostInput = new InvisibleInput( "<HostName>:<Port>..." );
		hostInputPanel.add(hostInput, BorderLayout.CENTER);
		hostInput.setColumns(10);
		
		connectArea.add( Box.createVerticalStrut( 2 ) );

		JPanel connecterPanel = new JPanel();
		connectArea.add(connecterPanel);
		connecterPanel.setLayout(new BorderLayout(0, 0));
		
		this.connecter = new ConnectButton("Connect");
		connecterPanel.add(connecter, BorderLayout.CENTER);
		connecterPanel.setMinimumSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		connecterPanel.setMaximumSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		connecterPanel.setPreferredSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		connecterPanel.setOpaque(false);
		
		JPanel settingsArea = new JPanel();
		settingsArea.setOpaque(false);
		sliderContent.add(settingsArea, BorderLayout.SOUTH);
		settingsArea.setLayout(new BoxLayout(settingsArea, BoxLayout.Y_AXIS));
		
		JPanel modePanel = new JPanel();
		modePanel.setOpaque(false);
		settingsArea.add(modePanel);
		modePanel.setLayout(new BorderLayout(0, 0));

		settingsArea.add( Box.createVerticalStrut( 1 ) );

		this.shortSelecter = new SelecterButton( SelecterButton.State.DEFAULT, SelecterButton.Side.LEFT, SelecterButton.Selecter.SHORT );
		modePanel.add(shortSelecter, BorderLayout.WEST);
		
		this.longSelecter = new SelecterButton( SelecterButton.State.SELECTED, SelecterButton.Side.RIGHT, SelecterButton.Selecter.LONG );
		modePanel.add(longSelecter, BorderLayout.EAST);
		
		JPanel factionPanel = new JPanel();
		factionPanel.setOpaque(false);
		settingsArea.add(factionPanel);
		factionPanel.setLayout(new BorderLayout(0, 0));
	
		settingsArea.add( Box.createVerticalStrut( 1 ) );

		this.securitySelecter = new SelecterButton( SelecterButton.State.SELECTED, SelecterButton.Side.LEFT, SelecterButton.Selecter.SECURITY );
		factionPanel.add(securitySelecter, BorderLayout.WEST);
		
		this.thiefSelecter = new SelecterButton( SelecterButton.State.DEFAULT, SelecterButton.Side.RIGHT, SelecterButton.Selecter.THIEF );
		factionPanel.add(thiefSelecter, BorderLayout.EAST);
		
		JPanel backPanel = new JPanel();
		backPanel.setMinimumSize( new Dimension( (int) GUISettings.sliderWidth, 20 ) );
		backPanel.setMaximumSize( new Dimension( (int) GUISettings.sliderWidth, 20 ) );
		backPanel.setPreferredSize( new Dimension( (int) GUISettings.sliderWidth, 20 ) );
		settingsArea.add(backPanel);
		backPanel.setLayout(new BorderLayout(0, 0));
		
		CancelButton backButton = new CancelButton("New button");
		backButton.addActionListener( event -> { this.slider.slideIn(); this.launcher.setState( LauncherButton.State.START ); } );
		backPanel.add(backButton, BorderLayout.CENTER);
				
		Component verticalStrut = Box.createVerticalStrut( (int) GUISettings.controlsHeight );
		slider.add(verticalStrut, BorderLayout.SOUTH);
		
		JPanel controlsWrapper = new JPanel();
		controlsWrapper.setOpaque(false);
		GridBagConstraints gbc_controlsWrapper = new GridBagConstraints();
		gbc_controlsWrapper.fill = GridBagConstraints.BOTH;
		gbc_controlsWrapper.gridx = 0;
		gbc_controlsWrapper.gridy = 0;
		wrapper.setLayer( controlsWrapper, 4 );
		wrapper.add(controlsWrapper, gbc_controlsWrapper);
		controlsWrapper.setLayout(new BorderLayout(0, 0));
		
		JPanel controlsContainer = new JPanel();
		controlsContainer.setOpaque(false);
		controlsContainer.setMinimumSize( new Dimension( controlsContainer.getMinimumSize().width, (int) GUISettings.controlsHeight ) );
		controlsContainer.setMaximumSize( new Dimension( controlsContainer.getMaximumSize().width, (int) GUISettings.controlsHeight ) );
		controlsContainer.setPreferredSize( new Dimension( controlsContainer.getPreferredSize().width, (int) GUISettings.controlsHeight ) );
		controlsWrapper.add(controlsContainer, BorderLayout.SOUTH);
		controlsContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel controls = new JPanel();
		controls.setBorder(new EmptyBorder(5, 5, 5, 5));
		controls.setMinimumSize( new Dimension( (int) GUISettings.sliderWidth, controls.getMinimumSize().height ) );
		controls.setMaximumSize( new Dimension( (int) GUISettings.sliderWidth, controls.getMaximumSize().height ) );
		controls.setPreferredSize( new Dimension( (int) GUISettings.sliderWidth, controls.getPreferredSize().height ) );
		controls.setOpaque( false );
		controlsContainer.add(controls, BorderLayout.EAST);
		controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
		
		this.launcher = new LauncherButton("Start Game");		
		launcher.setMinimumSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		launcher.setMaximumSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		launcher.setPreferredSize( new Dimension( (int) GUISettings.sliderWidth - 10, 30 ) );
		launcher.setActionCommand("");
		controls.add(launcher);
						
	}	
		
}
