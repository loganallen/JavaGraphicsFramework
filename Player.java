import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class Player{

	private int x, y, width, height;
	private double speed;
	private Rectangle2D.Double collisionRect;

	// Constructors
	public Player(){
		this.x = 150;
		this.y = 350;
		this.width = 50;
		this.height = 50;
		this.speed = 3.0;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
	}
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 40;
		this.speed = 3.0;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
	}
	public Player(int x, int y, int width, int height, double speed){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
	}

	// Movement methods
	public void moveRight(){
		this.x += speed;
	}public void moveLeft(){
		this.x -= speed;
	}public void moveUp(){
		this.y -= speed;
	}public void moveDown(){
		this.y += speed;
	}

	// Method returning if there is a collision
	public boolean collision(Rectangle2D.Double otherRect){
		return this.collisionRect.getBounds2D().intersects(otherRect.getBounds2D());
	}

	// Getter method for collision rectangle
	public Rectangle2D.Double getCollisionRect(){
		return this.collisionRect;
	}

	// Draw the player onto the graphics window
	public void draw(Graphics window, Color color){
		// Draw the player
		window.setColor(color);
    	window.fillRect(x,y,width,height);
    	// Draw player with an outline of black
		window.setColor(new Color(0,0,0));
    	window.drawRect(x,y,width,height);
    	// Update collision rectangle
    	this.collisionRect.setRect(x,y,width,height);
	}
}