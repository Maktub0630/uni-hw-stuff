public class RegularPolygonTester {
    /**
     * this class is to test RegularPolygon class.
     * @param args
     */
    public static void main(String[] args){
        RegularPolygon regularPolygon=new RegularPolygon(1,1); //generate a new object of regularpolygon, setNumOfSides will change the n if appropriate
        //try case of numOfSides and radius: n=1, r=1
        System.out.print("expected num of sides: 3\nexpected radius: 0\n"); //resultantly, n=3,r=1 since bouundary case and setRadius and setnumOfSides have changed the params
        System.out.print("number of sides: " + regularPolygon.getNumOfSides());
        System.out.print("\nradius: "+regularPolygon.getRadius());
        System.out.print("\n"); 
        //check if the coor. of the vertices are as expected.
        for (int i = 0; i < regularPolygon.getNumOfSides();i++) {
            System.out.print("x coordinate of No." + i + " local vertex: " + regularPolygon.getXLocal() [i]);
            System.out.print("\ny coordinate of No." + i + " local vertex: " + regularPolygon.getYLocal() [i]);
            System.out.print("\n\n");
        }
        //expected result of vertices
        System.out.print("expected x coordinate of No.0 local vertex: 1.0\nexpected y coordinate of No.0 local vertex: 0.0\n\nexpected x coordinate of No.1 local vertex: -0.4999999999999998\nexpected y coordinate of No.1 local vertex: -0.8660254037844387\n\nexpected x coordinate of No.2 local vertex: -0.5000000000000004\nexpected y coordinate of No.2 local vertex: 0.8660254037844384\n\n");
        //check if the contains function works or not
        boolean inshape=regularPolygon.contains(1,0 );

        System.out.print("x,y-coordinate of the targeted point: (1,0)\n");
        System.out.print("the shape contains the targeted point: ");
        System.out.println(inshape);
        System.out.println("expected ans: true");
    }
}
