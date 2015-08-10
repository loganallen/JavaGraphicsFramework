import javax.swing.*;
import java.awt.*;

public class BasicGraphicsRunner{
	
	public static void main(String[] args){

		// Establish width and height of graphics window
		int width = 640;
		int height = 460;

		// Create new JFrame
		JFrame myFrame = new JFrame();
		myFrame.setSize(width,height);
		myFrame.setTitle("Basic Graphics Program!");

		// Create BasicGraphics object and add to JFrame
		BasicGraphics window = new BasicGraphics(width,height);
		myFrame.add(window);

		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set JPanel with graphics focusable (enables KeyListener)
		window.setFocusable(true);
		window.requestFocusInWindow();

		// Call BasicGraphics' animate method to run game
		window.animate();

	}
}