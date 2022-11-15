/**
 * The Triangle class is used to model triangles.   Like  the  Square  class,  it  is  a  subclass  of  the 
Shape  class  and  it  inherits  all  the  instance  variables  and  methods  of  the  Shape  class.  The 
Triangle class overrides  the setVertices() method for setting the local  coordinates of the 3 
vertices of a standard triangle. Below is a detailed description for the Triangle class.
 * @author Herman Chung
 */
public class Triangle extends Shape{
    /** a method for setting the local coordinates of the 3 vertices of a standard triangle. 
     * Here, a standard triangle is an equilateral triangle having its center located at (0, 0) and one of its vertex on the positive x-axis of its local coordinate system. 
     * The parameter d specifies the distance from the center of the triangle to any of its vertices. 
     * @param d the  distance  from  the  center  of  the angle to any of its vertices.
     */
    public void setVertices(double d){
        xLocal=new double[3]; //here xLocal and yLocal are initialised with finite space. 
        yLocal=new double[3];
        xLocal[0]=d;xLocal[1]=(-d*Math.cos(Math.PI/3));xLocal[2]=(-d*Math.cos(Math.PI/3)); //coordinates are assigned to the respective space.
        yLocal[0]=0;yLocal[1]=(-d*Math.sin(Math.PI/3));yLocal[2]=(d*Math.sin(Math.PI/3)); //note that the number is rounded to int.
    }
}
