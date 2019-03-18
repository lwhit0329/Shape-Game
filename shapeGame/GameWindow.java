package uncp.csc1900.projects.shapeGame;

import javax.swing.JFrame;

/**
 * Create the window frame which will pop up and add the objects to window
 * 
 * @author Lewis A. Whitley
 * @version 09/20/2013
 */
public class GameWindow {

	/**
	 * Purpose to view the DrawPanel and GamePanel objects and give gui for
	 * later interaction
	 * 
	 * @param args
	 *            standard parameter for main method
	 */
	public static void main(String[] args) {
		JFrame win = new JFrame("Lab 2 Testing");
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GamePanel frame = new GamePanel(win);

		win.add(frame);

		win.pack();
		win.setVisible(true);
	}

}