package gui.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

import constants.Colors;
import gui.util.LauncherButton.State;
import util.GUISettings;

public class SelecterButton extends JButton {

	public static enum State {
		DEFAULT ( null ), 
		SELECTED ( Colors.blue );
		
		private Color color;
		
		private State( Color _color ) { this.color = _color; }
	
		public Color getColor() { return this.color; }
	}
	
	public static enum Side { LEFT, RIGHT; }
	public static enum Selecter {
	
		LONG ( "Long" ),
		SHORT ( "Short" ),
		SECURITY ( "Security" ),
		THIEF ( "Thief" );
		
		private String text;
		
		private Selecter( String _text ) { this.text = _text; }
		public String getText() { return this.text; }
		
	}
	
	public State state;
	private Side side;
	private Selecter selecter;
	
	
	public SelecterButton( State _state, Side _side, Selecter _selecter ) {
	
		this.state = _state;
		this.side = _side;
		this.selecter = _selecter;
		this.initButton();
		
	}
	
	private void initButton() {
		
		this.setMinimumSize( new Dimension( (int) ( ( GUISettings.sliderWidth - 2 ) / 2 ), 30 ) );
		this.setMaximumSize( new Dimension( (int) ( ( GUISettings.sliderWidth - 2 ) / 2 ), 30 ) );
		this.setPreferredSize( new Dimension( (int) ( ( GUISettings.sliderWidth - 2 ) / 2 ), 30 ) );
		
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		this.setRolloverEnabled( true );
		this.setOpaque( false );
		this.setBackground( null );
		this.setBorder( null );
						
	}

	
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D ) _graphics;
		
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		double x = ( this.side == Side.RIGHT ) ? width - 4 : 0;
		double y = 0;

		Color buttonBackground = ( ( this.getModel().isRollover() && this.state != State.SELECTED ) ? Colors.lightest : this.state.getColor() );
		
		Area buttonArea = new Area( new Rectangle2D.Double( x, y, 4, height ) );
		
		if( this.state == State.SELECTED ) {
			graphics.setPaint( new Color( 0, 0, 0, 50 ) );
			graphics.fillRect( 0, 0, width, height );
			graphics.setPaint( new Color( 0, 0, 0, 50 ) );
			
			Area border =  new Area( new Rectangle2D.Double( 0, 0, width, height ) );
			border.subtract( new Area( new Rectangle2D.Double( 1, 1, width - 2, height - 2 ) ) );
			
			graphics.fill( border );
		}

		if( buttonBackground != null ) {
			graphics.setPaint( buttonBackground );
			graphics.fill( buttonArea );
		}
				
		FontMetrics metrics = graphics.getFontMetrics();
		Rectangle2D textContainer = metrics.getStringBounds( this.selecter.getText(), graphics );
		int stringX = ( ( int )width / 2 ) - ( ( int )textContainer.getWidth() / 2 );
		int stringY = ( ( ( int )height + 1 ) / 2 ) - ( ( metrics.getAscent() + metrics.getDescent() ) / 2 ) + metrics.getAscent();
		
		graphics.setPaint( Color.WHITE );
		graphics.drawString( this.selecter.getText(), stringX, stringY );
		
	}

	
	
}
