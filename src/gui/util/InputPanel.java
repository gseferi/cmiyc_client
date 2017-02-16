package gui.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Colors;
import util.GUISettings;

public class InputPanel extends JPanel  {

	public InputPanel() {
		super(); this.initPanel();
	}
	
	public void initPanel() {
		this.setOpaque( true );
		this.setBorder( new EmptyBorder( 2, 2, 2, 2 ) );
	
		this.setMinimumSize( new Dimension( (int) (GUISettings.sliderWidth - 10), 30 ) );
		this.setMaximumSize( new Dimension( (int) (GUISettings.sliderWidth - 10), 30 ) );
		this.setPreferredSize( new Dimension( (int) (GUISettings.sliderWidth - 10), 30 ) );
		
	}
	
	public void paintComponent( Graphics _graphics ) {
		
		Graphics2D graphics = ( Graphics2D ) _graphics;

		graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

		int radius = 4;
		int width = this.getWidth();
		int height = this.getHeight();
		
		Area borderArea = new Area( new RoundRectangle2D.Double( 0, 0, width, height, radius, radius ) );
		Area borderSub = new Area( new RoundRectangle2D.Double( 2, 2, width-4, height-4, radius-2, radius-2 ) );
		
		borderArea.subtract( borderSub );
		
		graphics.setPaint( Colors.lightest );
		graphics.fill( borderArea );
		
	}
	
}
