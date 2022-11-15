/**
 * The Circle class is used to model circles. Like the Square class and the Triangle class, it is a 
subclass of the Shape class and it inherits all the instance variables and methods of the Shape 
class.  The  Circle  class  overrides  the  setVertices()  method  for  setting  the  local  coordinates 
of the upper left and lower right vertices of an axis-aligned bounding box of a standard circle, 
as well as the getX() and getY() methods for retrieving the canvas coordinates of the upper 
left  and  lower  right  vertices  of  this  bounding  box.  Below  is  a  detailed  description  for  the 
Circle class.
 * @author Herman Chung
 */
public class Circle extends Shape{
    /**
     * a method for setting the local coordinates of the upper left and lower right vertices of an axis-aligned bounding box of a standard circle. 
     * Here, a standard circle is a circle having its center located at (0, 0) of its local coordinate system. The parameter d specifies the radius of the circle.
     * @param d  the radius of the circle.
     */
    public void setVertices(double d){
        xLocal=new double[2]; //here xLocal and yLocal are initialised with finite space. 
        yLocal=new double[2];
        xLocal[0]=-d;xLocal[1]=d; //coordinates are assigned to the respective space.
        yLocal[0]=-d;yLocal[1]=d;
    }
    /**
     * method for getting the x-coordinates (the upper left and lower right vertices) of an axis-aligned bounding box of the circle in the canvas coordinate system.
     * 
     * @return xCanvas
     */
    public int[] getX(){
        int[] xCanvas = new int[xLocal.length]; //xCanvas array is generated.
        xCanvas[0]=(int) Math.round(xLocal[0]+xc); // having transformed from local to Canvas, the  coordinate will be assigned to the corresponding array space.
        xCanvas[1]=(int) Math.round(xLocal[1]+xc); //note that the number is rounded to int.
        return xCanvas;
    }
    /**
     * method for getting the y-coordinates (the upper left and lower right vertices) of an axis-aligned bounding box of the circle in the canvas coordinate system.
     * @return yCanvas
     */
    public int[] getY(){//same thing as above, only diff. is that this time it is done by yLocal and is assigned to yCanvas. 
        int[] yCanvas = new int[yLocal.length];
        yCanvas[0]=(int) Math.round(yLocal[0]+yc);
        yCanvas[1]=(int) Math.round(yLocal[1]+yc);
        return yCanvas;
    }
}
