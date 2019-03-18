package uncp.csc1900.projects.shapeGame;

import java.util.*;

public interface IDrawPanel {

	void addShape(IShape nShape);
	void moveShapes();
	ArrayList<IShape> getShapesAt(int x, int y);
	IShape removeShape(int id);
	ArrayList<IShape> removeShapes(ArrayList<Integer> ids);		
	void clearShapes();
	IShape getShape(int id);
	boolean isDebugging();
	void setDebug(boolean debug);
	int getNumberShapes();
	IShape getShapeByPosition(int pos);
}
