package gui.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import constants.Colors;

public class LauncherButton extends JButton {

	public static enum State {
		START( "Start Game" ),
		FIND( "Find Game" );
		
		private String text;
		
		private State( String _text ) { this.text = _text; }
		public String getText() { return this.text; }
		
	}
		
	private int radius = 4;
	
	public State state;
	
	private double bHeight;
	private double bWidth;
		
	public LauncherButton() { this.initButton(); }
	public LauncherButton( String _text ) { this.initButton(); }

	private void initButton() {
		
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		this.setRolloverEnabled( true );
		this.setOpaque( false );
		this.setBackground( null );
		this.setBorder( null );
		
		this.state = State.START;
		
		this.bWidth = this.getWidth();
		this.bHeight = this.getHeight();
		
	}
	
	public void setState( State _state ) {
		this.state = _state;
		this.revalidate();
		this.repaint();
	}
	
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D ) _graphics;
		
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		
		
		this.bWidth = this.getWidth();
		this.bHeight = this.getHeight();

		Color buttonBackground = ( ( this.getModel().isPressed() ) ? Colors.green.darker() : ( ( this.getModel().isRollover() ) ? Colors.green.brighter() : Colors.green ) );
		
		Area buttonArea = new Area( new RoundRectangle2D.Double( 0, 0, this.bWidth, this.bHeight, this.radius, this.radius ) );
		graphics.setPaint( buttonBackground );
		graphics.fill( buttonArea );
		
		
		FontMetrics metrics = graphics.getFontMetrics();
		Rectangle2D textContainer = metrics.getStringBounds( this.state.getText(), graphics );
		int stringX = ( ( int )this.bWidth / 2 ) - ( ( int )textContainer.getWidth() / 2 );
		int stringY = ( ( ( int )this.bHeight + 1 ) / 2 ) - ( ( metrics.getAscent() + metrics.getDescent() ) / 2 ) + metrics.getAscent();
		
		graphics.setPaint( Color.WHITE );
		graphics.drawString( this.state.getText(), stringX, stringY );
		
	}
		
}
