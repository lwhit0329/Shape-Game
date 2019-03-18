package uncp.csc1900.projects.shapeGame;

/**
 * To manipulate the vectors of the shape in order to more throughout the panel
 * 
 * @author Lewis A. Whitley
 * @version 10/1/2013
 */
public class Vector implements IVector {

	private int x;

	private int y;

	/**
	 * Purpose is to construct a new vector object to get the x and y
	 * coordinates
	 * 
	 * @param inputX
	 *            set the x variable with the new input
	 * 
	 * @param inputY
	 *            set the y variable with the new input
	 */
	public Vector(int inputX, int inputY) {
		x = inputX;
		y = inputY;
	}

	/**
	 * Purpose is to construct a new vector object to get the x and y
	 * coordinates
	 * 
	 * @param b
	 *            Pulls another vector that we will get the x and y from by our
	 *            other methods
	 */
	public Vector(IVector b) {
		x = b.getVectorX();
		y = b.getVectorY();
	}

	/**
	 * Purpose to return x value of a vector
	 * 
	 * @return an int that will hold the x value of the vector
	 */
	public int getVectorX() {
		return x;
	}

	/**
	 * Purpose to return y value of a vector
	 * 
	 * @return an int that will hold the y value of the vector
	 */
	public int getVectorY() {
		return y;
	}

	/**
	 * Purpose to add the value of the parameter to current vector and return
	 * the value
	 * 
	 * @param b
	 *            an IVector to be used to add with the current vector
	 * @return a new Vector that is the values of the two x and y values added
	 */
	public IVector add(IVector b) {
		int tempx = x + b.getVectorX();
		int tempy = y + b.getVectorY();
		return new Vector(tempx, tempy);
	}

	/**
	 * Purpose to subtract the value of the parameter from current vector and
	 * return the value
	 * 
	 * @param b
	 *            an IVector to be used to addsubtract from the current vector
	 * @return a new Vector that is the values of the two x and y values
	 *         subtracted
	 */
	public IVector subtract(IVector b) {
		int tempx = x - b.getVectorX();
		int tempy = y - b.getVectorY();
		return new Vector(tempx, tempy);
	}

	/**
	 * Purpose is to scale the x and y value of the current vector
	 * 
	 * @param scalar
	 *            will be a double value and used to change the value of current
	 *            vector values
	 */
	public void scale(double scalar) {
		x = (int) (x * scalar);
		y = (int) (y * scalar);
	}

	/**
	 * Purpose is to scale the x and y value separately of the current vector
	 * 
	 * @param scalarX
	 *            will be a double value and used to change the value of current
	 *            x vector values * @param scalarY will be a double value and
	 *            used to change the value of current y vector values
	 */
	public void scale(double scalarX, double scalarY) {
		x = (int) (x * scalarX);
		y = (int) (y * scalarY);
	}

	/**
	 * purpose to get the sum of the product of x and product of y components
	 * 
	 * @param b
	 *            An IVector give the x and y value to manipulate current x and
	 *            y value
	 * @return an int that will give the value of sum of x and y products
	 */
	public int dotProduct(IVector b) {
		int currentX = getVectorX();
		int currentY = getVectorY();
		int parameterX = b.getVectorX();
		int parameterY = b.getVectorY();
		return (currentX * parameterX + currentY * parameterY);
	}

	/**
	 * purpose to get the sum of the cross product of x and y values of current
	 * and new values
	 * 
	 * @param b
	 *            An IVector give the x and y value to manipulate current x and
	 *            y value
	 * @return an int that will give the cross product value of the x and ys
	 */
	public int crossProduct(IVector b) {
		int currentX = getVectorX();
		int currentY = getVectorY();
		int parameterX = b.getVectorX();
		int parameterY = b.getVectorY();
		return (currentX * parameterY - currentY * parameterX);
	}
}
