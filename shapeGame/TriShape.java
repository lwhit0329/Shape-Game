package uncp.csc1900.projects.shapeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * purpose is to create a triangle to be displayed within the panel
 * 
 * @author Lewis A. Whitley
 * @version 11/12/2013
 */
public class TriShape extends RectShape {

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
	public TriShape(int inputId, Point inputLocation, Dimension inputSize,
			Color inputCol, boolean isFill, IVector shapeSpeed, 
			int shapeValue) {

		super(inputId, inputLocation, inputSize, inputCol, isFill, shapeSpeed,
				shapeValue);
	}

	/**
	 * Purpose to draw the triangle with the given variables
	 * 
	 * @param g
	 *            the object that the oval will be drawn on
	 */
	public void draw(Graphics g) {
		Rectangle size = getBoundingBox();
		int[] arrayX = { (int) (size.getX()), (int) (size.getCenterX()),
			(int) (size.getX() + size.getWidth()) };
		int[] arrayY = { (int) (size.getY() + size.getHeight()),
			(int) (size.getMinY()), (int) (size.getY() + size.getHeight()) };
		if (g != null) {
			if (getColor() != null) {
				g.setColor(getColor());
			} else {
				g.setColor(Color.black);
			}
			if (isFilled()) {
				g.fillPolygon(arrayX, arrayY, 3);
			} else {
				g.drawPolygon(arrayX, arrayY, 3);
			}
		}
	}

	/**
	 * Purpose to display information that will be given when I want to debug
	 * the game
	 * 
	 * @return return the string that I am putting in the return statement that
	 *         will be seen when the game is in debug mode
	 */
	public String toString() {
		Rectangle size = getBoundingBox();
		return "Triangle (" + (int) getX() + ", " + (int) getY() + ") "
				+ (int) size.getWidth() + "x" + (int) size.getHeight();
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
		Rectangle size = getBoundingBox();
		Vector aP = new Vector((int) size.getX() - px,
				(int) (size.getY() + size.getHeight()) - py);
		Vector bP = new Vector(
				(int) (size.getX() + (size.getWidth() / 2)) - px,
				(int) (size.getY()) - py);
		Vector cP = new Vector((int) (size.getX() + (size.getWidth())) - px,
				(int) (size.getY() + size.getHeight()) - py);
		int aPxBP = aP.crossProduct(bP);
		int bPxCP = bP.crossProduct(cP);
		int cPxAP = cP.crossProduct(aP);
		return ((aPxBP <= 0 && bPxCP <= 0 && cPxAP <= 0) || (aPxBP >= 0
				&& bPxCP >= 0 && cPxAP >= 0));

	}

}
