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
import gui.util.LauncherButton.State;

public class CancelButton extends JButton {
					
	public CancelButton() { this.initButton(); }
	public CancelButton( String _text ) { this.initButton(); }
	
	private void initButton() {
		
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		this.setRolloverEnabled( true );
		this.setOpaque( false );
		this.setBackground( null );
		this.setBorder( null );
		this.setFocusable( true );
						
	}
		
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D ) _graphics;
		
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		
		
		int width = this.getWidth();
		int height = this.getHeight();

		Color buttonBackground = ( ( this.getModel().isPressed() ) ? Colors.red.darker() : ( ( this.getModel().isRollover() ) ? Colors.red.brighter() : Colors.red ) );
		
		Area buttonArea = new Area( new Rectangle2D.Double( 0, 0, width, height ) );
		graphics.setPaint( buttonBackground );
		graphics.fill( buttonArea );
		
		
		FontMetrics metrics = graphics.getFontMetrics();
		Rectangle2D textContainer = metrics.getStringBounds( "Back", graphics );
		int stringX = ( ( int )width / 2 ) - ( ( int )textContainer.getWidth() / 2 );
		int stringY = ( ( ( int )height + 1 ) / 2 ) - ( ( metrics.getAscent() + metrics.getDescent() ) / 2 ) + metrics.getAscent();
		
		graphics.setPaint( Color.WHITE );
		graphics.drawString( "Back", stringX, stringY );
		
	}

	
	
}
