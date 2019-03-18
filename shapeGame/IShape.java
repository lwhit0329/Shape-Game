package uncp.csc1900.projects.shapeGame;

import java.awt.*;

public interface IShape {

    Point getLocation();    
    Rectangle getBoundingBox();
    int getX();
    int getY();
    void move(Rectangle moveArea);
    Color getColor();
    int getId();
    boolean isFilled();
    boolean isUnfilled();
    IVector getMoveVector();
    String toString();
    void draw(Graphics g);
    int getValue();    
    boolean contains(int px, int py);
}
 
	
