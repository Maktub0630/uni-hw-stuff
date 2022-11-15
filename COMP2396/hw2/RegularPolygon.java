/**
 * The RegularPolygon class is a subclass of the Shape class and is used to model regular n-sided polygons. 
 * Besides the properties it inherited from the Shape class, the RegularPolygon class also declares a number of private instance variables for storing the number of sides and the radius of a polygon. 
 * It has public getters and setters for accessing its private instance variables. 
 * It also has public methods for setting the local coordinates of the vertices of a polygon and for checking if a point (in the canvas coordinate system) is contained by a polygon. 
 * Below is a detailed description for the RegularPolygon class.
 */
public class RegularPolygon extends Shape{
    /**
     * a constructor for building a regular n-sided polygon with a radius of r.
     * @param n
     * @param r
     */
    public RegularPolygon(int n, double r){ //constructor
        this.setNumOfSides(n); //set the number of sides with setter
        this.setRadius(r); //set the radius with setter
        this.setVertices(); //set the vertives of xLocal with method
    }
    /**
     * a constructor for building a regular n-sided polygon with a radius of 1.0.
     * @param n
     */
    public RegularPolygon(int n){
        this(n,1.0);
    }
    /**
     * a constructor for building a regular 3-sided polygon with a radius of 1.0.
     */
    public RegularPolygon(){
        this(3,1.0);
    }
    /**
     * an integer value specifying the number of sides of the regular n-sided polygon.
     */
    private int numOfSides;
    /**
     * a double value specifying the radius of the regular n-sided polygon.
     */
    private double radius;
    /**
     * a method for retrieving the number of sides of the regular polygon.
     * @return numOfSides
     */
    public int getNumOfSides(){
        return numOfSides;
    }
    /**
     * a method for retrieving the radius of the regular polygon.
     * @return radius
     */
    public double getRadius(){
        return radius;
    }
    /**
     * a method for setting the number of sides of the regular n-sided polygon.
     * @param n
     */
    public void setNumOfSides(int n){
        if (n < 3){
            n = 3;
        }
        numOfSides=n;
        setXLocal(new double[numOfSides]); //re-initialize the array to update the change of numOfSides or radius
        setYLocal(new double[numOfSides]);
        setVertices(); //reset the vertices
    }
    /**
     * a method for setting the radius of the regular n-sided polygon.
     * @param r
     */
    public void setRadius(double r){
        if (r < 0){
            r = 0;
        }
        radius=r;
        
        setXLocal(new double[numOfSides]);
        setYLocal(new double[numOfSides]);
        setVertices();
    }
    /**
     * a method for setting the local coordinates of the vertices of the regular n-sided polygon based on its number of sides and radius.
     */
    public void setVertices(){
        double a;
        if (numOfSides%2==0){
            //set initial vertex of a even-sided regular polygon
            a=Math.PI/numOfSides;
            getXLocal() [0]=radius*Math.cos(a); 
            getYLocal() [0]=radius*Math.sin(a);
        }
        else{
            //set initial vertex of a odd-sided regular polygon
            a=0;
            getXLocal() [0]=radius;
            getYLocal() [0]=0.0;
        }
        //set up vertices of succeeding points
        for ( int i = 1; i<numOfSides; i++ ){
            getXLocal() [i]=radius*Math.cos(a-i*2*Math.PI/numOfSides);
            getYLocal() [i]=radius*Math.sin(a-i*2*Math.PI/numOfSides);
        }
    }
    /**
     * a method for determining if a point (x, y) in the screen coordinate system is contained by the regular n-sided polygon.
     * A point is considered to be contained by a polygon if it lies either completely inside the polygon, or on any of the sides or vertices of the polygon.
     * @param x x-coordinate of the target point
     * @param y y-coordinate of the target point
     * @return returns boolean value whether the point is in shape or not
     */
    public boolean contains(double x, double y){
        //convert the point from canvas to local
        double xL=(x-getXc())*Math.cos(-getTheta())-(y-getYc())*Math.sin(-getTheta());
        double yL=(x-getXc())*Math.sin(-getTheta())+(y-getYc())*Math.cos(-getTheta());
        double u=xL;
        for ( int i=0; i<=numOfSides; i++){
            if (getXLocal() [numOfSides/2]>u){ //return false if the point is outside of the shape
                return false;
            }
            else{//if inside, rotate the point and check again
                u=xL*Math.cos(2*i*Math.PI/numOfSides)-yL*Math.sin(2*i*Math.PI/numOfSides);
            }
        }
        return true;
    }
}
