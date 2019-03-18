package uncp.csc1900.projects.shapeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 * Class used to take the DrawPanel draw it, and add shapes to the DrawPanel
 * 
 * @author Lewis A. Whitley
 * @version 10/3/2013
 */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Random rand = new Random();
	private int id = 1;
	private DrawPanel panel;
	/** Timer for movement */
	protected Timer mShape;
	/** Timer for creating shapes */
	protected Timer cShape;
	private ScoringPanel score;
	private Point mousePoint;

	/**
	 * Purpose to add DrawPanel to a window to display
	 * 
	 * @param window
	 *            pass a window though to add objects to
	 */
	public GamePanel(JFrame window) {
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
		mousePoint = null;
		panel = new DrawPanel(Color.white);
		panel.setBorder(BorderFactory.createTitledBorder(""));
		JPanel buttonPanel = new JPanel();
		FlowLayout fLayout = new FlowLayout();
		buttonPanel.setLayout(fLayout);

		JButton quit = new JButton("Click");
		JButton reset = new JButton("Reset Game");
		quit.setName("quit");
		reset.setName("reset");
		quit.addActionListener(new QuitWindow(window));
		reset.addActionListener(new ResetGame());
		score = new ScoringPanel();
		buttonPanel.add(score);
		buttonPanel.add(reset);
		buttonPanel.add(quit);

		for (int i = 0; i <= 5; i++) {
			panel.addShape(createRandomShape());
		}

		// Show testing make sure code is correct
		panel.setSize(200, 200);

		// panel.setDebug(true);

		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.EAST);
		mShape = new Timer(50, new ShapeMovement());
		cShape = new Timer(500, new CreateShapes());
		mShape.start();
		cShape.start();
		panel.addAncestorListener(new StopTimers());
		panel.addMouseListener(new Mouse());

	}

	/**
	 * Purpose to create a listener for the quit button to exit out of the
	 * program
	 * 
	 * @author Lewis A. Whitley
	 * @version 10/3/2013
	 */
	private class QuitWindow implements ActionListener {
		JFrame closeListener = new JFrame();

		/**
		 * Purpose to construct the QuitWindow object that the listener will
		 * attach with
		 * 
		 * @param nFrame
		 */
		private QuitWindow(JFrame nFrame) {
			closeListener = nFrame;
		}

		/**
		 * Purpose to tell what action to perform once the listener is triggered
		 * in this case to close the window
		 * 
		 * @param arg0
		 *            This is passing what listener is going to be triggered in
		 *            this case the pressing of the button
		 */
		public void actionPerformed(ActionEvent press) {
			if (closeListener != null) {
				closeListener.dispose();
			}
		}
	}

	/**
	 * Purpose to create a listener to reset the game when button is pressed
	 * 
	 * @author Lewis A. Whitley
	 * @version 11/25/2013
	 */

	private class ResetGame implements ActionListener {

		/**
		 * Purpose to tell the action listener to reset the game when pressed
		 * 
		 * @param event
		 *            This is passing what listener is going to be triggered in
		 *            this case the pressing of the button
		 */

		public void actionPerformed(ActionEvent event) {
			score.resetScore();
			panel.clearShapes();
			for (int i = 0; i <= 5; i++) {
				panel.addShape(createRandomShape());
			}

		}

	}

	/**
	 * Purpose to create a listener for all of the mouse actions
	 * 
	 * @author Lewis A. Whitley
	 * @version 11/25/2013
	 */

	private class Mouse implements MouseListener {

		/**
		 * Purpose to tell the mouseListener what to do when clicked
		 * 
		 * @param event
		 *            This will be information for mouse event
		 */

		public void mouseClicked(MouseEvent event) {
			// nothing here

		}

		/**
		 * Purpose to tell the mouseListener what to do when enter game space
		 * 
		 * @param event
		 *            This will be information for mouse event
		 */

		public void mouseEntered(MouseEvent event) {
			cShape.start();
			mShape.start();

		}

		/**
		 * Purpose to tell the mouseListener what to do when exit game space
		 * 
		 * @param event
		 *            This will be information for mouse event
		 */
		public void mouseExited(MouseEvent event) {
			cShape.stop();
			mShape.stop();

		}

		/**
		 * Purpose to tell the mouseListener what to do when pressed and which
		 * button is pressed to debug or store data
		 * 
		 * @param event
		 *            This will be information for mouse event
		 */
		public void mousePressed(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				mousePoint = new Point(event.getX(), event.getY());
			}
			if (event.getButton() == MouseEvent.BUTTON3) {
				panel.setDebug(true);
			}

		}

		/**
		 * Purpose to tell the mouseListener what to do when pressed and what
		 * button is pressed to stop debug or remove shapes and inc score
		 * 
		 * @param event
		 *            This will be information for mouse event
		 */

		public void mouseReleased(MouseEvent event) {
			final int MAX = 5;

			if (event.getButton() == MouseEvent.BUTTON1 && mousePoint != null
					&& Math.abs(mousePoint.getX() - event.getX()) <= MAX
					&& Math.abs(mousePoint.getY() - event.getY()) <= MAX) {

				ArrayList<IShape> contains = new ArrayList<IShape>(
						panel.getShapesAt(event.getX(), event.getY()));

				Iterator<IShape> removeIter = contains.iterator();
				while (removeIter.hasNext()) {
					IShape shape = removeIter.next();
					int tempId = shape.getId();
					int value = shape.getValue();
					score.incScore(value);
					panel.removeShape(tempId);
				}
			}

			if (event.getButton() == MouseEvent.BUTTON3) {
				panel.setDebug(false);
			}

		}

	}

	/**
	 * Purpose to get timer to move shapes
	 * 
	 * @author Lewis A. Whitley
	 * @version 11/8/2013
	 */
	private class ShapeMovement implements ActionListener {
		/**
		 * Purpose call move method on a timer
		 * 
		 * @param arg0
		 *            Argument passing to timer event
		 */
		public void actionPerformed(ActionEvent event) {
			panel.moveShapes();
		}
	}

	/**
	 * Purpose To create shapes up to 30
	 * 
	 * @author Lewis A. Whitley
	 * @version 11/8/2013
	 */
	private class CreateShapes implements ActionListener {
		/**
		 * Purpose add shapes on a timer when less than 31
		 * 
		 * @param arg0
		 *            Argument passing to timer event
		 */
		public void actionPerformed(ActionEvent event) {
			int count = panel.getNumberShapes();
			int max = 30;
			if (count < max) {
				panel.addShape(createRandomShape());
			}
		}
	}

	/**
	 * Purpose stop timers when window is closed
	 * 
	 * @author Lewis A. Whitley
	 * @version 11/8/2013
	 */
	private class StopTimers implements AncestorListener {

		/**
		 * Purpose nothing
		 * 
		 * @param arg0
		 *            nothing
		 */
		public void ancestorAdded(AncestorEvent event) {
			// empty code

		}

		/**
		 * Purpose nothing
		 * 
		 * @param arg0
		 *            nothing
		 */
		public void ancestorMoved(AncestorEvent event) {
			// empty code

		}

		/**
		 * Purpose stop timers when the window is closed
		 * 
		 * @param arg0
		 *            Argument passing to stop timer
		 */
		public void ancestorRemoved(AncestorEvent event) {
			mShape.stop();
			cShape.stop();

		}

	}

	/**
	 * Purpose to generate a random size
	 * 
	 * @return random Dimension for shape
	 */
	protected Dimension pickRandomSize() {
		int width = rand.nextInt(76) + 25;
		int height = rand.nextInt(76) + 25;
		return new Dimension(width, height);
	}

	/**
	 * Purpose to generate a random value
	 * 
	 * @return random an int to be the value of the shape
	 */
	protected int pickRandomValue() {
		return ((rand.nextInt(5) + 1) * 10);
	}

	/**
	 * Purpose to generate a random IVector
	 * 
	 * @return random Vector for the shapes movement
	 */
	protected IVector pickRandomVector() {
		int x = rand.nextInt(11) - 5;
		int y = rand.nextInt(11) - 5;
		if (x == 0 && y == 0) {
			x = 1;
			y = -1;
		}
		return new Vector(x, y);
	}

	/**
	 * Purpose to generate a random color
	 * 
	 * @return random color to the random generated shape
	 */
	protected Color pickRandomColor() {
		Color col;
		int set = rand.nextInt(5);
		switch (set) {
		case 0:
			col = Color.red;
			break;
		case 1:
			col = Color.blue;
			break;
		case 2:
			col = Color.green;
			break;
		case 3:
			col = Color.yellow;
			break;
		case 4:
			col = Color.orange;
			break;
		default:
			col = Color.black;
		}
		return col;
	}

	/**
	 * Purpose to generate a random point
	 * 
	 * @return random Point for the shape to appear at
	 */
	protected Point pickRandomPoint() {
		int w = panel.getWidth();
		int h = panel.getHeight();
		int x;
		int y;
		Dimension temp;
		if (w <= 0) {
			temp = panel.getPreferredSize();
			w = (int) temp.getWidth();
			x = rand.nextInt(w + 1);
		} else {
			x = rand.nextInt(w + 1);
		}
		if (h <= 0) {
			temp = panel.getPreferredSize();
			h = (int) temp.getHeight();
			y = rand.nextInt(h + 1);
		} else {
			y = rand.nextInt(h + 1);
		}
		return new Point(x, y);
	}

	/**
	 * Purpose to generate a random IShape
	 * 
	 * @return random IShape to be added to the panel
	 */
	protected IShape createRandomShape() {
		int set = rand.nextInt(3);
		IShape shape;
		switch (set) {
		case 0:
			shape = new RectShape(id, pickRandomPoint(), pickRandomSize(),
					pickRandomColor(), rand.nextBoolean(), pickRandomVector(),
					pickRandomValue());
			break;
		case 1:
			shape = new OvalShape(id, pickRandomPoint(), pickRandomSize(),
					pickRandomColor(), rand.nextBoolean(), pickRandomVector(),
					pickRandomValue());
			break;
		default:
			shape = new TriShape(id, pickRandomPoint(), pickRandomSize(),
					pickRandomColor(), rand.nextBoolean(), pickRandomVector(),
					pickRandomValue());
		}
		id++;
		return shape;
	}

}
