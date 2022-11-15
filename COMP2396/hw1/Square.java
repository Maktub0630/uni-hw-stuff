/**
* The Square class is used to model squares.  It is a subclass of the Shape class and it inherits all 
the  instance  variables  and  methods  of  the  Shape  class.  The  Square  class  overrides  the 
setVertices() method for setting the local coordinates of the 4 vertices of a standard square. 
Below is a detailed description for the Square class. 
* @author Herman Chung
*/

public class Square extends Shape{
    /**
    * a method for setting the local coordinates of the 4 vertices of a standard square. 
    * Here, a standard square has its center located at (0, 0) and its sides being parallel to the x- and y-axes of its local coordinate system. 
    * The parameter d specifies half-the-length of a side of the square.
    @param d  half-the-length of a side of the square
    */
    public void setVertices(double d){
        xLocal=new double[4];//here xLocal and yLocal are initialised with finite space. 
        yLocal=new double[4];
        xLocal[0]=d;xLocal[1]=d;xLocal[2]=-d;xLocal[3]=-d;//coordinates are assigned to the respective space.
        yLocal[0]=d;yLocal[1]=-d;yLocal[2]=-d;yLocal[3]=d;//note that the number is rounded to int.
    }
}