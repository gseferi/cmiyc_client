package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import constants.Colors;
import game.constants.GameSettings;
import game.constants.GameSettings.Arena;
import util.Debug;

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
	
	private double increaseFraction = 1;
	private double speed;
	
	private boolean[] keyArray = new boolean[256];

	public double myX = 300, myY = 300, mouseX, mouseY;
	
	private Timer timer = new Timer(17, this);
	private boolean lock = false;
	
	public GameScreen() {
		super();
		this.setMinimumSize( GameSettings.Arena.outerSize );
		this.setOpaque(false);
		this.setBackground(null);

		this.speed = 3;

		this.setFocusable( true );
		this.requestFocus();
		this.requestFocusInWindow();
		this.setFocusTraversalKeysEnabled( false );

		this.addKeyListener( this );
		addMouseMotionListener(this);
		timer.start();
	}

	
	/**
	 * To set changeFraction field so we can resize easily 
	 * @param newWidth after resize
	 * @param newHeight after resize 
	 */
	public void setFraction(double newWidth, double newHeight) {
		
		double increaseInWidth = newWidth / Arena.outerSize.width; // Get the ratio of increase (or decrease) for width
		double increaseInHeight = newHeight / Arena.outerSize.height; // Get the ratio of increase (or decrease) for height
		
		// Debug.say("inc w: " + increaseInWidth + " - inc h: " + increaseInHeight );
		
		if (increaseInWidth == increaseInHeight) this.increaseFraction = increaseInWidth; // If the ratio of change in dimension stayed proportionate, then changeFraction normally
		else if (increaseInWidth > increaseInHeight) this.increaseFraction = increaseInHeight; // If there is more width change than height, changeFraction increases according to its height
		else this.increaseFraction = increaseInWidth; // If there is more height change than width, changeFraction increases according to its width
	}
	
	private void regainFocus() {
		this.setFocusable( true );
		this.requestFocus();
		this.requestFocusInWindow();
		this.setFocusTraversalKeysEnabled( false );
	}
	
	public void paintComponent(Graphics g) {

		this.regainFocus();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );

		this.setFraction( this.getWidth(), this.getHeight() );
		
		double playerX = this.myX * this.increaseFraction;
		double playerY = this.myY * this.increaseFraction;
		
		double playerD = 20 * increaseFraction;
		
		double outerArenaWidth = GameSettings.Arena.outerSize.width * increaseFraction;
		double outerArenaHeight =  GameSettings.Arena.outerSize.height * increaseFraction;
		
		Area cursor = new Area(new Ellipse2D.Double(mouseX-4, mouseY-4, 8, 8));
		Area rect = new Area(new Rectangle2D.Double(0, 0, outerArenaWidth, outerArenaHeight));
		Area circle = new Area(new Ellipse2D.Double( playerX - ( playerD / 2 ) , playerY - ( playerD / 2 ), playerD, playerD ));
		
		Area container = new Area( new Rectangle2D.Double( 0, 0, this.getWidth(), this.getHeight() ) );
		
		double lightRadius = 70 * increaseFraction;
		double angle = getMouseAngle( playerX, playerY, mouseX, mouseY);
		double extent = 360 * (GameSettings.Security.lightArcPercentage / 100);
		
		Arc2D light = new Arc2D.Double( playerX - ( lightRadius / 2 ), playerY - ( lightRadius / 2 ), lightRadius, lightRadius, Math.toDegrees(-(angle + Math.toRadians((extent/2)))), extent, Arc2D.PIE ) ;
		Area flashlight = new Area(light);
		
		if (circle.contains(mouseX, mouseY)) lock = true;
		else lock = false;
		
		g2.setPaint( Colors.lighter );
		g2.fill(rect);
		g2.fill(container);
		g2.setPaint(Colors.lightest);
		g2.fill(flashlight);
		g2.setPaint( Colors.green );
		g2.fill(circle);
		g2.setPaint(new Color(255,255,255, 100));
		g2.fill(cursor);
		
	}

	public boolean isKeyDown(int keyCode){
		return keyArray[keyCode];
	}
	
	
	public void move() {
		
		double angle = getMouseAngle(myX * increaseFraction, myY * increaseFraction, mouseX, mouseY);
		if (lock) return;
		
		if (keyArray[KeyEvent.VK_W]) {
			myX += speed * Math.cos(angle);
			myY += speed * Math.sin(angle);
		} 
		if (keyArray[KeyEvent.VK_S]) {
			myX -= speed * Math.cos(angle);
			myY -= speed * Math.sin(angle);
		}
		if (keyArray[KeyEvent.VK_D]) {
			myX += speed * Math.cos(angle + Math.toRadians(90));
			myY += speed * Math.sin(angle + Math.toRadians(90));	
		}
		if (keyArray[KeyEvent.VK_A]) {
			myX += speed * Math.cos(angle - Math.toRadians(90));
			myY += speed * Math.sin(angle - Math.toRadians(90));	
		}
		if (keyArray[KeyEvent.VK_SPACE]) {
			
		}
	}
	
	
	public double getMouseAngle(double myX, double myY, double mouseX, double mouseY) {

		if (myX != mouseX && myY != mouseY) {
			double xdif = (mouseX - myX);
			double ydif = (mouseY - myY);
			double angle = 0; // in radians
			angle = -Math.atan(ydif / xdif);
			if (xdif < 0) {
				if (ydif < 0) {
					angle += Math.PI;
				} else {
					angle -= Math.PI;
				}
			}
			return -angle;
		} else if (myX > mouseX) {
			return Math.PI;
		} else if (myX < mouseX) {
			return 0.0;
		} else if (myY > mouseY) {
			return -Math.PI / 2.0;
		} else if (myY < mouseY) {
			return Math.PI / 2.0;
		}
		return 0.0;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		this.mouseX = arg0.getX();
		this.mouseY = arg0.getY();		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		this.mouseX = arg0.getX();
		this.mouseY = arg0.getY();
	}

	public void keyPressed(KeyEvent arg0) {
		this.keyArray[arg0.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent arg0) {
		this.keyArray[arg0.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) {

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		move();
		
	}

}
