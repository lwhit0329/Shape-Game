package uncp.csc1900.projects.shapeGame;

public interface IVector
{

 int getVectorX();

 int getVectorY();

 IVector add(IVector b);
 
 IVector subtract(IVector b);

 void scale(double  scalar);
 
 void scale(double scalarX, double scalarY);
 
 int dotProduct(IVector b);
 
 int crossProduct(IVector b);
}
