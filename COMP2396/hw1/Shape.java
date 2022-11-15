import java.awt.Color;
import java.lang.Math;;
/**
 * This is a superclass to model general shape.  It  has  instance  variables  for  storing  color, 
fill-type, orientation, canvas coordinates of the center, and local coordinates of the vertices of 
a shape. It has methods for getting the canvas coordinates of the vertices of a shape. Below is 
a detailed description for the Shape class.
 * @author Herman Chung
 */
public class Shape{ 
    //these are the instance variable. Here, all of the instance variables will be declared.
     /** a Color object specifying the color of the shape. */
	public Color color;
     /** a boolean value specifying whether the shape is filled or not filled.*/
	public boolean filled;
     /** a double value specifying the orientation (in radians) of the shape in the canvas coordinate system. */
	public double theta;
     /** a double value specifying the x-coordinate of the center of the shape in the canvas coordinate system. */
	public double xc;
     /** a double value specifying the y-coordinate of the center of the shape in the canvas coordinate system. */
	public double yc;
    /** an array of double values specifying the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system.*/
    public double[] xLocal; //since there are multiple vertices, array is used instead. 

     /** an array of double values specifying the y-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system. 
     */
    public double[] yLocal;
    
    /**
     * a method for setting the local coordinates of the vertices of a shape. 
     * This is a dummy method and is supposed to be overridden in the subclasses.
     * @param d local coordinate
     */
    public void setVertices(double d){

    }
    /**
     * a method for translating the center of the shape by dx and dy, respectively, 
     * along the x and y directions of the canvas coordinate system.
     * @param dx translation distance of the center of x coordinate
     * @param dy translation distance of the center of y coordinate
     */
    public void translate(double dx,double dy){
        xc+=dx; // here, dx and dy should be added to xc and yc respectively.
        yc+=dy;
    }
    /**
     * a method for rotating the shape about its center by an angle of dt (in radians).
     * @param dt rotation angle
     */
    public void rotate(double dt){
        theta+=dt; //here, dt is added to theta.
    }
    /**
     * method for retrieving the x-coordinates of the vertices (in counter-clockwise order) of the shape in the canvas coordinate system (rounded to nearest integers).
     * 
     * @return xCanvas (return the Canvas X coordinates)
     */
    public int[] getX(){
        int[] xCanvas = new int[xLocal.length]; //here, an array  is generated to store Canvax X coordinates.
        for (int i=0;i < xLocal.length;i++ ){
            xCanvas[i]=(int) Math.round((xLocal[i] * Math.cos(theta))-(yLocal[i] * Math.sin(theta))+xc); // x'=x*cos(theta)-y*sin(theta)+xc
        }
        return xCanvas; 
    }
    /**
     * method for retrieving the y-coordinates of the vertices (in counter-clockwise order) of the shape in the canvas coordinate system (rounded to nearest integers).
     * @return yCanvas (return the Canvas Y coordinates)
     */
    public int[] getY(){
        int[] yCanvas = new int[yLocal.length];  //here, an array  is generated to store Canvax X coordinates.
        for (int i=0;i < yLocal.length;i++ ){
            yCanvas[i]=(int) Math.round((xLocal[i] * Math.sin(theta))+(yLocal[i] * Math.cos(theta))+yc); // x'=x*sin(theta)+y*cos(theta)+yc
        }
        return yCanvas; //return the Canvas Y coordinates
    }
}