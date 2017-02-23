package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import constants.Colors;
import game.constants.GameSettings;
import launcher.Main;

public class OfflineScreen extends JPanel {

	private Main client;
	
	public OfflineScreen( Main _client ) {
		this.client = _client;
	}
	
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D )_graphics;
		
		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

		graphics.setPaint( Colors.lighter );
		graphics.fillRect( 0, 0, this.getWidth(), this.getHeight() );
		
		double titleAreaWidth = 320;
		double titleAreaHeight = 200;
		double titleAreaX = ( this.getWidth() / 2 ) - 160;
		double titleAreaY = 0;
		
		double playerRadius = 10;
		
		graphics.setPaint( Colors.lightest );
		graphics.fill( new Arc2D.Double( titleAreaX - 35, ( titleAreaHeight / 2 ) - 35, 70, 70, -45, 90.0, Arc2D.PIE ) );

		graphics.fill( new Arc2D.Double( titleAreaX + 80 - 35, ( titleAreaHeight / 2 ) - 15 - 35, 70, 70, -120, 60.0, Arc2D.PIE ) );

		graphics.fill( new Arc2D.Double( titleAreaX + 105 - 35, ( titleAreaHeight / 2 ) - 15 - 35, 70, 70, -120, 60.0, Arc2D.PIE ) );

		graphics.fill( new Arc2D.Double( titleAreaX + 160 - 35, ( titleAreaHeight / 2 ) +15 - 35, 70, 70, 70, 40.0, Arc2D.PIE ) );

		graphics.fill( new Arc2D.Double( titleAreaX + 220 - 35, ( titleAreaHeight / 2 ) + 15 - 35, 70, 70, 40, 100.0, Arc2D.PIE ) );
		graphics.fill( new Arc2D.Double( titleAreaX + 220 - 35, ( titleAreaHeight / 2 ) + 15 - 35, 70, 70, -160, 40.0, Arc2D.PIE ) );

		graphics.fill( new Arc2D.Double( titleAreaX + 280 - 35, ( titleAreaHeight / 2 ) - 35, 70, 70, -45, 90.0, Arc2D.PIE ) );

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius, ( titleAreaHeight / 2 ) - playerRadius, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1, ( titleAreaHeight / 2 ) - playerRadius + 1, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 80, ( titleAreaHeight / 2 ) - playerRadius - 15, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 80, ( titleAreaHeight / 2 ) - playerRadius + 1 - 15, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 160, ( titleAreaHeight / 2 ) - playerRadius + 15, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 160, ( titleAreaHeight / 2 ) - playerRadius + 1 + 15, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 160, ( titleAreaHeight / 2 ) - playerRadius - 15 - 30, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.red );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 160, ( titleAreaHeight / 2 ) - playerRadius + 1 - 15 - 30, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 220, ( titleAreaHeight / 2 ) - playerRadius + 15, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 220, ( titleAreaHeight / 2 ) - playerRadius + 1 + 15, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 280, ( titleAreaHeight / 2 ) - playerRadius, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 280, ( titleAreaHeight / 2 ) - playerRadius + 1, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player

		//Player
		graphics.setPaint( new Color( 0, 0, 0, 50 ) );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 105, ( titleAreaHeight / 2 ) - playerRadius - 15, playerRadius*2, playerRadius*2 ) );
		graphics.setPaint( Colors.green );
		graphics.fill( new Ellipse2D.Double( titleAreaX - playerRadius + 1 + 105, ( titleAreaHeight / 2 ) - playerRadius + 1 - 15, playerRadius*2 - 2, playerRadius*2 - 2 ) );
		//Player
	
		graphics.setFont(new Font("default", Font.BOLD, 23));

	
		FontMetrics metrics = graphics.getFontMetrics();
		Rectangle2D textContainer = metrics.getStringBounds( "Catch Me If You Can", graphics );
		int stringX = ( ( int )this.getWidth() / 2 ) - ( ( int )textContainer.getWidth() / 2 );
		int stringY = ( ( ( int )this.getHeight() + 1 ) / 2 ) - ( ( metrics.getAscent() + metrics.getDescent() ) / 2 ) + metrics.getAscent();

		graphics.setPaint( Colors.lightest );
		graphics.drawString( "Catch Me If You Can", stringX, 180 );
		
	}
	
}
