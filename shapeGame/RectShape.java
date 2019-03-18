package uncp.csc1900.projects.shapeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * purpose is to create a rectangle to be displayed within the panel
 * 
 * @author Lewis A. Whitley
 * @version 10/1/2013
 */
public class RectShape implements IShape {
	private int id;

	private Point centerxy;

	private Dimension size;

	private Color col;

	private int value;

	private boolean fill;

	private IVector vector;

	/**
	 * Purpose is to create a Rectangle with all needed informaiton for display
	 * 
	 * @param inputId
	 *            The ID used for the shape while debugging
	 * 
	 * @param inputLocation
	 *            The location of the center point of the rectangle
	 * 
	 * @param inputSize
	 *            The width and height of the shape givin
	 * @param inputCol
	 *            The color we will draw our shape
	 * @param isFill
	 *            The boolean value to know if out shape will be filled or not
	 * @param shapeSpeed
	 *            The vector amount to know the speed of the movement of the
	 *            shape
	 * @param shapeValue
	 *            The int that shows the shapes value while debugging
	 */
	public RectShape(int inputId, Point inputLocation, Dimension inputSize,
			Color inputCol, boolean isFill, IVector shapeSpeed, 
			int shapeValue) {
		int min = 25;
		id = inputId;
		if (inputLocation != null) {
			centerxy = new Point(inputLocation);
		} else {
			centerxy = new Point(0, 0);
		}
		if (inputSize != null) {
			size = new Dimension(inputSize);
		} else {
			size = new Dimension(min, min);
		}
		if (size.getWidth() < min) {
			size.setSize(min, size.getHeight());
		}
		if (size.getHeight() < min) {
			size.setSize(size.getWidth(), min);
		}
		if (inputCol != null) {
			col = inputCol;
		} else {
			col = Color.black;
		}
		fill = isFill;
		if (shapeSpeed != null) {
			vector = new Vector(shapeSpeed);
		} else {
			vector = new Vector(1, 1);
		}
		value = shapeValue;
	}

	/**
	 * Purpose is to return the center point of the shape
	 * 
	 * @return will return a Point object that is the center of the rectangle
	 */
	public Point getLocation() {
		Point center = new Point(centerxy);
		return center;
	}

	/**
	 * Purpose to get the measurement of the shapes bounding box
	 * 
	 * @return this will return a rectangle object that will have the
	 *         measurements of the bounding box
	 */
	public Rectangle getBoundingBox() {
		return new Rectangle((int) (centerxy.getX() - size.getWidth() / 2),
				(int) (centerxy.getY() - size.getHeight() / 2),
				(int) size.getWidth(), (int) size.getHeight());
	}

	/**
	 * Purpose is to get the x coordinate of the center of the shape
	 * 
	 * @return an int that is the x coordinate of the center
	 */
	public int getX() {
		return (int) centerxy.getX();
	}

	/**
	 * Purpose is to get the y coordinate of the center of the shape
	 * 
	 * @return an int that is the y coordinate of the center
	 */
	public int getY() {
		return (int) centerxy.getY();
	}

	/**
	 * purpose to move the shape on screen and if hits wall will change vector
	 * to bounce back in the screen
	 * 
	 * @param moveArea
	 *            this parameter will be used to get the dimensions of the game
	 *            area to make sure that the shapes will stay in the four sides.
	 */
	public void move(Rectangle moveArea) {
		// step i
		int x = (int) centerxy.getX();
		int y = (int) centerxy.getY();
		x = x + vector.getVectorX();
		y = y + vector.getVectorY();
		centerxy = new Point(x, y);

		if (moveArea != null) {
			// step ii
			Rectangle tempRect = getBoundingBox();

			// step iii
			boolean fit = moveArea.contains(tempRect);

			// step iv
			if (!fit) {
				// step v part 1
				int vDirectionX = 1;
				int vDirectionY = 1;

				// step v part 2
				// side values of the rectangle
				int shapeLeft = (int) (tempRect.getX());
				int shapeTop = (int) (tempRect.getY());
				int shapeRight = (int) (tempRect.getX() + tempRect.getWidth());
				int shapeBottom = (int) (tempRect.getY() 
						+ tempRect.getHeight());
				// side values of the movement area
				int movementLeft = (int) moveArea.getX();
				int movementTop = (int) moveArea.getY();
				int movementRight = (int) (moveArea.getX() + moveArea
						.getWidth());
				int movementBottom = (int) (moveArea.getY() + moveArea
						.getHeight());

				// step v part 3
				if (shapeLeft < movementLeft) {
					vDirectionX = -1;
					centerxy.setLocation((int) centerxy.getX()
							- (2 * (shapeLeft - movementLeft)),
							(int) centerxy.getY());
				}

				// step v part 4
				if (shapeRight > movementRight) {
					vDirectionX = -1;
					centerxy.setLocation((int) centerxy.getX() - 2
							* (shapeRight - movementRight),
							(int) centerxy.getY());
				}

				// step v part 5
				if (shapeTop < movementTop) {
					vDirectionY = -1;
					centerxy.setLocation((int) centerxy.getX(),
							(int) centerxy.getY() - 2
									* (shapeTop - movementTop));
				}

				// step v part 6
				if (shapeBottom > movementBottom) {
					vDirectionY = -1;
					centerxy.setLocation((int) centerxy.getX(),
							(int) centerxy.getY() - 2
									* (shapeBottom - movementBottom));
				}
				vector.scale(vDirectionX, vDirectionY);
			}
		}
	}

	/**
	 * Purpose to get the color object of shape
	 * 
	 * @return returns a the color that is set to the shape
	 */
	public Color getColor() {
		return col;
	}

	/**
	 * Purpose is to get the shapes designated id
	 * 
	 * @return return an int that is the shapes id
	 */
	public int getId() {
		return id;
	}

	/**
	 * purpose is to find out if the shape is to be filled
	 * 
	 * @return return a boolean value if the shape is to be filled or not
	 */
	public boolean isFilled() {
		return fill;
	}

	/**
	 * Purpose is to find out if the shape is going to be an outline
	 * 
	 * @return return the opposite of the fill variable value as a boolean
	 */
	public boolean isUnfilled() {
		return !fill;
	}

	/**
	 * Purpose is to get the value of the vector movement
	 * 
	 * @return a copy of the variable vector that is the shapes vector movement
	 *         value as an IVector
	 */
	public IVector getMoveVector() {
		return new Vector(vector);
	}

	/**
	 * Purpose to draw the rectangle with the givin variables
	 * 
	 * @param g
	 *            the object that the rectangle will be drawn on
	 */
	public void draw(Graphics g) {
		if (g != null) {
			g.setColor(col);
			if (fill) {
				g.fillRect((int) (centerxy.getX() - size.getWidth() / 2),
						(int) (centerxy.getY() - size.getHeight() / 2),
						(int) size.getWidth(), (int) size.getHeight());
			} else {
				g.drawRect((int) (centerxy.getX() - size.getWidth() / 2),
						(int) (centerxy.getY() - size.getHeight() / 2),
						(int) size.getWidth(), (int) size.getHeight());
			}
		}
	}

	/**
	 * purpose is to get the assigned value for the shape
	 * 
	 * @return return an int value of the assigned amount for value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * purpose to tell us yes or no if the shape contains the given coordinates
	 * 
	 * @param px
	 *            this will be the amount that x will be searched for in the
	 *            shape
	 * @param py
	 *            this will be the amount that y will be searched for in the
	 *            shape
	 * @return return a boolean type that will state if the coordinates are in
	 *         the shape or not
	 */
	public boolean contains(int px, int py) {
		Rectangle tempRect = getBoundingBox();
		int x1 = (int) tempRect.getX();
		int y1 = (int) tempRect.getY();
		int x2 = (int) (tempRect.getX() + tempRect.getWidth());
		int y2 = (int) (tempRect.getY() + tempRect.getHeight());
		return x1 <= px && px <= x2 && y1 <= py && py <= y2;
	}

	/**
	 * Purpose to display information that will be given when I want to debug
	 * the game
	 * 
	 * @return return the string that I am putting in the return statement that
	 *         will be seen when the game is in debug mode
	 */
	public String toString() {
		return "Rectangle (" + (int) centerxy.getX() + ", "
				+ (int) centerxy.getY() + ") " + (int) size.getWidth() + "x"
				+ (int) size.getHeight();
	}
}
