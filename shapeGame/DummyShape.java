package uncp.csc1900.projects.shapeGame;

import java.awt.*;

// -------------------------------------------------------------------------
/**
 *  Creates a DummyShape to be used in testing.
 *  
 *  This shape is a red oval at (25, 25) that is 50x100 in size.
 *
 *  @author  saturner
 *  @version Jul 25, 2013
 */
public class DummyShape implements IShape {

	// ----------------------------------------------------------
	/**
	 * Constructor for a DummyShape
	 */
	public DummyShape() {
		// do nothing
	}

	@Override
	public Point getLocation() {
		return null;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(25, 25, 50, 100);
	}

	@Override
	public int getX() {
		return 50;
	}

	@Override
	public int getY() {
		return 75;
	}

	@Override
	public void move(Rectangle moveArea) {
		// does nothing
	}

	@Override
	public Color getColor() {
		return Color.red;
	}

	@Override
	public int getId() {
		return -1;
	}

	@Override
	public boolean isFilled() {
		return true;
	}

	@Override
	public IVector getMoveVector() {
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(25, 25, 50, 100);
		
	}
	
	@Override
	public String toString() {
		return "Dummy Shape";
	}

	@Override
	public int getValue() {
		return -1;
	}

	@Override
	public boolean isUnfilled() {
		return false;
	}

	@Override
	public boolean contains(int px, int py) {
		return false;
	}

}
