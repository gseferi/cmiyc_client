package graphics;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

/**
 * This represents the game screen 
 * @author Rinaldy
 *
 */
public class GameDrawer extends Pane {

	private double mouseX, mouseY;
	private double myX = 300.0, myY = 300.0;
	private double myRadius = 25.0, cursorRadius = 7;
	private double speed = 4.0;

	
	private Circle cursor, player; 
	
	// About the flashlight
	private Arc flashlight;
	private double flashRad = 200;
	private double flashRange = 40;
	private double extent = 180;
	
	/**
	 * Initialises everything
	 */
    public GameDrawer() {
    	this.setStyle("-fx-background-color: black;");
    	this.setPrefSize(840, 530);
    	this.cursor = new Circle(0, 0, cursorRadius, Color.SKYBLUE);
    	this.player = new Circle(myX, myY, myRadius, Color.LIGHTGREEN);
    	
    	this.flashlight = new Arc(myX, myY, flashRad, flashRad, 0 - 22.5, 45);
    	this.flashlight.setType(ArcType.ROUND);
    
    	this.flashlight.setFill(Color.YELLOW);
    	this.moveMouse();
    	this.movePlayer();

    }
    
    /**
     * This adds the mouse listener to move the cursor in the screen
     */
    public void moveMouse() {
		
    	this.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
    		this.mouseX = e.getSceneX(); 
    		this.mouseY = e.getSceneY();
    		this.cursor.setCenterX(this.mouseX);
    		this.cursor.setCenterY(this.mouseY);
    		double angle = getMouseAngle(myX, myY, mouseX, mouseY);
    		this.flashlight.setRotate(Math.toDegrees(angle));
    	});
    	this.getChildren().add(cursor);
    }  
    
    /**
     * This adds the key listener to move the player around
     */
    public void movePlayer() {
    	player.setFocusTraversable(true);
    	
    	this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {    	
    		
    		double angle = getMouseAngle(myX, myY, mouseX, mouseY);
    		if (e.getCode() == KeyCode.W) {
    			myX += speed * Math.cos(angle);
    			myY += speed * Math.sin(angle);
    			player.setCenterX(myX);
    			player.setCenterY(myY);
    			flashlight.setCenterX(myX);
    			flashlight.setCenterY(myY);
    		} 
    		if (e.getCode() == KeyCode.A) {
    			myX += speed * Math.cos(angle - Math.toRadians(90));
    			myY += speed * Math.sin(angle - Math.toRadians(90));
    			player.setCenterX(myX);
    			player.setCenterY(myY);
    			flashlight.setCenterX(myX);
    			flashlight.setCenterY(myY);
    		} 
    		if (e.getCode() == KeyCode.S) {
    			myX -= speed * Math.cos(angle);
    			myY -= speed * Math.sin(angle);
    			player.setCenterX(myX);
    			player.setCenterY(myY);
    			flashlight.setCenterX(myX);
    			flashlight.setCenterY(myY);
    		} 
    		if (e.getCode() == KeyCode.D) {
    			myX += speed * Math.cos(angle + Math.toRadians(90));
    			myY += speed * Math.sin(angle + Math.toRadians(90));
    			player.setCenterX(myX);
    			player.setCenterY(myY);
    			flashlight.setCenterX(myX);
    			flashlight.setCenterY(myY);
    		} 
    	});    	
    	
    	this.getChildren().add(player);
//    	this.getChildren().add(flashlight);
    }
    
    /**
     * Helper method to return the angle from a point in radians
     * @param myX Where I am at
     * @param myY Where I am at
     * @param mouseX Where I am looking
     * @param mouseY Where I am looking
     * @return An angle
     */
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
    
   
}
