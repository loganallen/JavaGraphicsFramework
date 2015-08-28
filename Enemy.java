import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class Enemy{

	private int x, y, width, height;
	private double speed;
	private Rectangle2D.Double collisionRect;
	private boolean up, down, left, right;
	private int screenWidth, screenHeight;

	// Constructors
	public Enemy(int screenWidth, int screenHeight){
		this.x = 350;
		this.y = 150;
		this.width = 60;
		this.height = 60;
		this.speed = 2.0;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
		this.up = true;
		this.down = false;
		this.right = true;
		this.left = false;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	public Enemy(int screenWidth, int screenHeight, int x, int y){
		this.x = x;
		this.y = y;
		this.width = 60;
		this.height = 60;
		this.speed = 2.0;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
		this.up = true;
		this.down = false;
		this.right = true;
		this.left = false;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	public Enemy(int screenWidth, int screenHeight, int x, int y, int width, int height, double speed){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.collisionRect = new Rectangle2D.Double(x,y,width,height);
		this.up = true;
		this.down = false;
		this.right = true;
		this.left = false;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	// Movement methods
	public void move(){
		//Animate the enemy box to bounce back and forth
		System.out.println("Moving");
		if(right){
		    x += speed;
		    if(x + width >= screenWidth){
		    	right = false;
		    	left = true;
		    }
		}else if(left){
		    x -= speed;
		    if(x <= 0){
		    	right = true;
		    	left = false;
		    }
		}
		if(up){
		    y -= speed;
		    if(y <= 0){
		    	up = false;
		    	down = true;
		    }
		}else if(down){
		    y += speed;
		    if(y + height >= screenHeight){
		    	up = true;
		    	down = false;
		    }
		}
		
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