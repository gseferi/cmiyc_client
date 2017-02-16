package gui.util;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import constants.Colors;

@SuppressWarnings("serial")
public class InvisibleInput extends JTextField {

    private String placeholder;

    public InvisibleInput() { super(); this.initiate(); }
    public InvisibleInput( final Document pDoc, final String pText, final int pColumns ) { super(pDoc, pText, pColumns); this.initiate(); }
    public InvisibleInput( final int pColumns ) { super(pColumns); this.initiate(); }
    public InvisibleInput( final String pText ) { super(); this.setPlaceholder( pText ); this.initiate(); }
    public InvisibleInput( final String pText, final int pColumns ) { super( pText, pColumns ); this.initiate(); }

    public String getPlaceholder() { return placeholder; }

    public void initiate() {
    	this.setBorder( new EmptyBorder( 0, 10, 0, 10 ) );
    	this.setOpaque(false);
    	this.setForeground( Colors.white );
    }
    
    public void setLimit( int _limit ) {
    	this.setDocument( new CharLimit( _limit ) );
    }
    
    @Override
    protected void paintComponent( final Graphics _graphics ) {
    	
        super.paintComponent( _graphics );
        
        if( this.placeholder.length() == 0 || getText().length() > 0 ) { return; }

        final Graphics2D g = (Graphics2D) _graphics;
        
        g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        
        FontMetrics metrics = g.getFontMetrics();
        
        g.setColor( Colors.lightest );
        g.drawString( this.placeholder, this.getInsets().left, ( 0 + ( ( this.getHeight() + 1 ) / 2 ) - ( ( metrics.getAscent() + metrics.getDescent() ) / 2 ) + metrics.getAscent() ) );
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

	
}
