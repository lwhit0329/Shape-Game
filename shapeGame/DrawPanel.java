package uncp.csc1900.projects.shapeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * Represent area which shapes will be drawn for shape game
 * 
 * @author Lewis A. Whitley
 * @version 09/20/2013
 */
public class DrawPanel extends JPanel implements IDrawPanel {
	private static final long serialVersionUID = 1L;
	// variable to hold debug state
	private boolean debug;
	private ArrayList<IShape> shapes;

	/**
	 * Purpose is to construct a new panel to store shapes
	 * 
	 * @param backGroundColor
	 *            set to the background of the panel once created
	 */
	public DrawPanel(Color backGroundColor) {
		if (backGroundColor != null) {
			setBackground(backGroundColor);
		} else {
			setBackground(Color.white);
		}
		shapes = new ArrayList<IShape>();
		debug = false;
		setPreferredSize(new Dimension(500, 500));
	}

	/**
	 * Purpose is to add the shape of the Panel that all of the IShapes will
	 * display
	 * 
	 * @param nShape
	 *            This is an object of IShape and will build its area
	 */
	public void addShape(IShape nShape) {
		if (nShape != null) {
			shapes.add(nShape);
			repaint(0, 0, getWidth(), getHeight());
		}
	}

	/**
	 * Purpose is to move all of the shapes on the display area
	 */
	public void moveShapes() {
		Rectangle r = new Rectangle(getWidth(), getHeight());
		Iterator<IShape> shapeIter = shapes.iterator();
		while (shapeIter.hasNext()) {
			IShape shape = shapeIter.next();
			shape.move(r);
			repaint(0, 0, getWidth(), getHeight());
		}
	}

	/**
	 * Purpose to return all shapes at the position given
	 * 
	 * @param x
	 *            these parameters will be used to locate the shapes in the
	 *            given x coordinate
	 * @param y
	 *            these parameters will be used to locate the shapes in the
	 *            given y coordinate
	 * @return an ArrayList of all the shapes that are IShape located at the
	 *         given x,y coordinate.
	 */
	public ArrayList<IShape> getShapesAt(int x, int y) {
		ArrayList<IShape> retShape = new ArrayList<IShape>();
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).contains(x, y)) {
				retShape.add(shapes.get(i));
			}
		}
		return retShape;
	}

	/**
	 * Purpose to remove a shape with the given id
	 * 
	 * @param id
	 *            uses the id given to find the shape that connects with it to
	 *            be removed
	 * @return an IShape will be returned to see which shape was removed
	 */
	public IShape removeShape(int id) {
		IShape shape;
		for (int i = 0; i <= shapes.size() - 1; i++) {
			shape = shapes.get(i);
			if (id == shape.getId()) {
				shapes.remove(i);
				repaint();
				return shape;
			}
		}
		return null;
	}

	/**
	 * Purpose to remove a list of shapes
	 * 
	 * @param ids
	 *            a list of integers for id will be passed to match with other
	 *            shapes
	 * @return an ArrayList of IShapes so the shapes removed will be identified
	 */
	public ArrayList<IShape> removeShapes(ArrayList<Integer> ids) {
		ArrayList<IShape> retShape = new ArrayList<IShape>();
		if (ids != null) {
			for (int i = 0; i <= shapes.size() - 1; i++) {
				for (int j = 0; j <= ids.size() - 1; j++) {
					if (shapes.get(i).getId() == ids.get(j)) {
						retShape.add(shapes.get(i));
						shapes.remove(i);
					}
				}
			}
		}
		repaint();
		return retShape;
	}

	/**
	 * Purpose to remove all shapes from a drawing area
	 */
	public void clearShapes() {
		shapes.clear();
		repaint();
	}

	/**
	 * Purpose gathers the information for the shape with matching id
	 * 
	 * @param id
	 *            this will be used to search the IShapes to match with the
	 *            correct id
	 * @return an IShape related to the passing id
	 */
	public IShape getShape(int id) {
		IShape shape;
		for (int i = 0; i <= shapes.size() - 1; i++) {
			shape = shapes.get(i);
			if (id == shape.getId()) {
				return shape;
			}
		}
		return null;
	}

	/**
	 * Purpose to find out if the program is in debug mode
	 * 
	 * @return a boolean that is the status of the value of debug variable
	 */
	public boolean isDebugging() {
		return debug;
	}

	/**
	 * Purpose to set the debug variable state
	 * 
	 * @param newDebug
	 *            this will be the status that debug will be after this method
	 *            is called
	 */
	public void setDebug(boolean newDebug) {
		debug = newDebug;
	}

	/**
	 * Purpose to get the amount of shapes in the DrawPanel class
	 * 
	 * @return an int which will be the amount of shapes in the DrawPanel
	 */
	public int getNumberShapes() {
		return shapes.size();
	}

	/**
	 * Purpose get the IShape at the given position
	 * 
	 * @param pos
	 *            will be the position that will be tagged for all shape objects
	 *            in that area
	 * @return the IShape object that was in given position
	 */
	public IShape getShapeByPosition(int pos) {
		if (pos <= shapes.size() - 1 && pos >= 0) {
			return shapes.get(pos);
		}
		return null;
	}

	/**
	 * Purpose to give the user information about number of shapes and debug
	 * status
	 * 
	 * @return a String that will hold the text for number and debug info
	 */
	public String toString() {
		String ret = "Number Of Shapes " + getNumberShapes() + "Is Debugging "
				+ isDebugging();
		return ret;
	}

	/**
	 * Purpose to override the paintComponent method and to display bounding box
	 * with debug info
	 * 
	 * @param g
	 *            will pass the object of shape that we want to be drawn
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color col = Color.black;
		Iterator<IShape> shapeIter = shapes.iterator();
		while (shapeIter.hasNext()) {
			IShape shape = shapeIter.next();
			shape.draw(g);
			if (debug) {
				g.setColor(col);
				Rectangle r = shape.getBoundingBox();
				g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(),
						(int) r.getHeight());
				g.drawString(shape.toString(), (int) r.getX(),
						((int) r.getY()) + 10);
				g.drawString("Vaule " + Integer.toString(shape.getValue()),
						(int) r.getX(), (int) (r.getY() + r.getHeight()));
			}

		}
	}
}