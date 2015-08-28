import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class BasicGraphics extends JPanel implements MouseListener, KeyListener{

	// Declare variables here
	private BufferedImage buffered;
	private Graphics windowTemp;

	private Color backgroundColor;

	private int mouseX;
	private int mouseY;
	private boolean[] key = new boolean[250];

	private Font font1 = new Font(Font.SERIF,Font.PLAIN,30);
	
	private Player player;
	private Enemy enemy1;
	private boolean moveRight, moveLeft;


	// CONSTRUCTOR
	public BasicGraphics(int width, int height){

		// Call JPanel constructor with doubleBuffered value
		super(true);
		this.setSize(width,height);
		// Add key and mouse listeners
		addMouseListener(this);
		addKeyListener(this);

		// Instantiate variables here
		this.backgroundColor = new Color(255,255,255);

		this.player = new Player(150,300,40,40,3.0);
		this.enemy1 = new Enemy(width,height);

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

		player.draw(windowTemp,new Color(0,0,150));
		enemy1.draw(windowTemp,new Color(150,0,0));

        // Display whether or not there is a collision
        if(player.collision(enemy1.getCollisionRect())){
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
			    player.moveRight();
			}
			if(key[65]){
			    player.moveLeft();
			}
			if(key[83]){
			    player.moveDown();
			}
			if(key[87]){
			    player.moveUp();
			}
	
			enemy1.move();
					
			
			
			
			 // Repaint the window
			repaint();
		}
	}

    //----------------- OTHER METHODS HERE -------------------- //


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
