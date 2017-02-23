package gui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import constants.Colors;
import util.GUISettings;

public class Slider extends JPanel implements ActionListener {

	public static enum Action { IN, OUT; }
	public static enum State { SLIDING_OUT, SLIDING_IN, STANDBY }
	
	private Timer timer;
	
	private double maxWidth = GUISettings.sliderWidth;
	private double minWidth = 0;
	
	public Action sAction;
	public State sState;
	
	private double speed;
	
	private JLayeredPane wrapper;
	
	
	public Slider( JLayeredPane _wrapper ) {
		super();
		this.sAction = Action.OUT;
		this.sState = State.STANDBY;
		
		this.wrapper = _wrapper;
		
		
		this.setMaximumSize( new Dimension( 0, this.getMaximumSize().height ) );
		this.setPreferredSize( new Dimension( 0, this.getPreferredSize().height ) );
		
				
		this.timer = new Timer( 5, this );
		this.speed = 7;
	}
	
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D ) _graphics;
		
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

		Color border = new Color( 37, 39, 43 );
		
		graphics.setPaint( Colors.darker );
		graphics.fillRect( 0, 0, this.getWidth(), this.getHeight() );
		
		graphics.setPaint( border );
		graphics.fillRect( 0, 0, 2, this.getHeight() );
		
	}
	
	public void slide() {
		this.timer.start();
	}
	
	public void slideIn() {
		
		this.sAction = Action.IN;
		this.timer.start();
		
	}
	
	public void slideOut() {
		this.sAction = Action.OUT;
		this.timer.start();
	}
	
	private void updateWidth() {
		
		switch( this.sAction ) {
			case IN:
				if( this.getWidth() > this.minWidth ) {
					this.sState = State.SLIDING_IN;
					this.setMaximumSize( new Dimension( ( int )( this.getWidth() - this.speed ), this.getMaximumSize().height ) );
					this.setPreferredSize( new Dimension( ( int )( this.getWidth() - this.speed ), this.getPreferredSize().height ) );
				}
				else { this.timer.stop();this.sAction = Action.OUT;this.sState = State.STANDBY; }
				break;
			case OUT:
				double curWidth = this.getWidth();
				double speed = this.speed;
				if( curWidth < this.maxWidth ) {
					this.sState = State.SLIDING_OUT;
					if( ( this.maxWidth - curWidth ) < this.speed ){ speed = ( this.maxWidth - curWidth ); }
					this.setMaximumSize( new Dimension( ( int )( this.getWidth() + speed ), this.getMaximumSize().height ) );
					this.setPreferredSize( new Dimension( ( int )( this.getWidth() + speed ), this.getPreferredSize().height ) );
				}
				else { this.timer.stop(); this.sAction = Action.IN; this.sState = State.STANDBY; }
				break;
		}
		
		this.revalidate();
		this.repaint();
		
		this.wrapper.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		this.updateWidth();
		
	}
	
}
