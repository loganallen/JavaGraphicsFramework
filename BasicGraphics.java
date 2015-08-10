import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

// © Copyright 2015 Logan Allen //

public class BasicGraphics extends JPanel implements MouseListener, KeyListener{

	//Instantiate variables here
	private BufferedImage buffered;
	private Graphics windowTemp;

	private Color backgroundColor;

	private int mouseX;
	private int mouseY;
	private boolean[] key = new boolean[250];

	private Font font1 = new Font(Font.SERIF,Font.PLAIN,30);
	
	private int boxX, boxY, boxWidth, boxHeight;
	private int playerX, playerY, playerWidth, playerHeight, playerSpeed;

	private boolean moveRight, moveLeft;

	private boolean collision;

	// Instantiate collision rectangles for each object
	private Rectangle2D.Double boxRect, playerRect;

	// CONSTRUCTOR
	public BasicGraphics(int width, int height){

		// Call JPanel constructor with doubleBuffered value
		super(true);
		// Add key and mouse listeners
		addMouseListener(this);
		addKeyListener(this);

		this.backgroundColor = new Color(255,255,255);

		this.boxX = 450;
		this.boxY = 100;
		this.boxWidth = 75;
		this.boxHeight = 75;
		this.boxRect = new Rectangle2D.Double(boxX,boxY,boxWidth,boxHeight);

		this.playerX = 150;
		this.playerY = 300;
		this.playerWidth = 40;
		this.playerHeight = 40;
		this.playerRect = new Rectangle2D.Double(playerX,playerY,playerWidth,playerHeight);
		this.playerSpeed = 3;

		this.moveLeft = false;
		this.moveRight = true;

	}

	public void paintComponent(Graphics window){
		// Call super paintComponent
		super.paintComponent(window);
		
		//Create a buffered Image
		if(buffered==null)
		   buffered = (BufferedImage)(createImage(getWidth(),getHeight()));

		//set gaphics window to buffered graphics
		windowTemp = buffered.createGraphics();

		// Fill window with background color or image
		windowTemp.setColor(this.backgroundColor);
		windowTemp.fillRect(0,0,getWidth(),getHeight());

		// Draw a string off screen to eradicate first drawString initialization lag
		windowTemp.drawString("Initialization lag",0,-100);

		/*
			_____ USEFUL GRAPHICS METHODS TO IMPLEMENT ____

		.setColor((r,g,b))
		.fillRect(x,y,width,height)
		.drawRect(x,y,width,height)
		.draw3DRect(x,y,width,height,true)
		.fillOval(x,y,width,height)
		.drawOval(x,y,width,height)
		.setFont(font)
		.drawString("string",x,y)
		.drawImage(image,x,y,this)

		*/

		// __________ Draw objects below __________ //


		// Draw the enemy box
		windowTemp.setColor(new Color(150,0,0));
	        windowTemp.fillRect(boxX,boxY,boxWidth,boxHeight);
	        // Draw box with an outline of black
		windowTemp.setColor(new Color(0,0,0));
	        windowTemp.drawRect(boxX,boxY,boxWidth,boxHeight);
	        // Update collision rectangle
	        boxRect.setRect(boxX,boxY,boxWidth,boxHeight);


        	// Draw the player
		windowTemp.setColor(new Color(0,0,150));
        	windowTemp.fillRect(playerX,playerY,playerWidth,playerHeight);
        	// Draw player with an outline of black
		windowTemp.setColor(new Color(0,0,0));
        	windowTemp.drawRect(playerX,playerY,playerWidth,playerHeight);
        	// Update collision rectangle
        	playerRect.setRect(playerX,playerY,playerWidth,playerHeight);


	        // Display whether or not there is a collision
	        if(playerCollision()){
			windowTemp.setColor(new Color(0,150,0));
	        	windowTemp.setFont(font1);
	        	windowTemp.drawString("Collision!",30,50);
        	}



		// Last element: draw buffered window onto screen
		window.drawImage(buffered, 0, 0, null);
	}

	// ANIMATE
	public void animate(){

		// Loop indefinitely
		while(true){
			// Wait for .01 seconds between displays
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		        //CODE GOES BELOW HERE
		        /*	
		       	      __key__ 		     __code__
		        	 a 		 	65
		        	 b 		 	66
		        	 c 		 	67
		        	 ...	 		...
		        	 z		 	90
		          	left arrow		37
		          	up arrow		38
		          	right			39
		          	down			40
		          	enter 			13

			-- Find ALL the key codes at this url:
			http://www.cambiaresearch.com/articles/15/javascript-char-codes-key-codes

	        	*/

			//Key code operations
			if(key[68]){
			    playerX += playerSpeed;
			}
			if(key[65]){
			    playerX -= playerSpeed;
			}
			if(key[83]){
			    playerY += playerSpeed;
			}
			if(key[87]){
			    playerY -= playerSpeed;
			}
	
	
			//Animate the enemy box to bounce back and forth
			if(moveRight){
			    boxX += 2;
			    if(boxX + boxWidth >= getWidth()){
			    	moveRight = false;
			    	moveLeft = true;
			    }
			}
			else if(moveLeft){
			    boxX -= 2;
			    if(boxX <= 0){
			    	moveLeft = false;
			    	moveRight = true;
			    }
			}
			
			
			
			 // Repaint the window
			repaint();
		}
	}

    //----------------- OTHER METHODS HERE -------------------- //

	// Collision detection
	private boolean playerCollision(){
	
		boolean collision = false;
		
		// Check for collision with any objects
		if(playerRect.getBounds2D().intersects(boxRect.getBounds2D())){
		    collision = true;
		}
		
		return collision;
	}


	 // MOUSELISTENER
	public void mouseClicked(MouseEvent e) {

		// Store the clicked positions into your mouse variables
		mouseX = e.getX();
		mouseY = e.getY();
		
		System.out.println("You clicked: " + mouseX + ", " + mouseY);
	}
	public void mouseEntered(MouseEvent e) {
	
	}
	public void mouseExited(MouseEvent e) {
	
	}
	public void mousePressed(MouseEvent e) {
	
	}
	public void mouseReleased(MouseEvent e) {
	
	}

	// KEYLISTENER
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		//S et the key code element in the array to TRUE
		key[e.getKeyCode()]=true;
		
		paint(getGraphics());
	}
	public void keyReleased(KeyEvent e) {
		// Set the key code element in the array to FALSE
		key[e.getKeyCode()]=false;

	}


}
