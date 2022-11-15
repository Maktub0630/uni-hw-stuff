import java.awt.Color;

public class ShapeTester {
    
    public static void main(String[] args){
        //generate new object
        Shape shape=new Shape();
        //set params of the object
        shape.setColor(Color.BLUE);
        shape.setFilled(true);
        shape.setTheta(1000);
        //use demo coor for testing
        double xLocal[]={1.0 , -0.4999999999999998, -0.5000000000000004};
        double yLocal[]={0.0 , -0.8660254037844387, 0.8660254037844384};
        shape.setXLocal(xLocal);
        shape.setYLocal(yLocal);
        shape.setXc(10);
        shape.setYc(10);
        //test getColor
        System.out.print("\ncolor: " + shape.getColor());
        System.out.print("\nexpected color: java.awt.Color[r=0,g=0,b=255]\n");
        //test getFilled
        System.out.print("\nfilled: " + shape.getFilled());
        System.out.print("\nexpected filled: true\n");
        //test getTheta
        System.out.print("\ntheta: " + shape.getTheta());
        System.out.print("\nexpected theta: 1000.0\n");
        //test getXLocal and getYLocal
        for (int i = 0; i < shape.getXLocal().length;i++){
            System.out.print("\nx coordinate of No." + i + " local vertex: " +shape.getXLocal() [i]);
            System.out.print("\ny coordinate of No." + i + " local vertex: " +shape.getYLocal() [i]);
            System.out.print("\n" );
        }
        System.out.print("\nexpected x coordinate of No.0 local vertex: 1.0\nexpected y coordinate of No.0 local vertex: 0.0\n\nexpected x coordinate of No.1 local vertex: -0.4999999999999998\nexpected y coordinate of No.1 local vertex: -0.8660254037844387\n\nexpected x coordinate of No.2 local vertex: -0.5000000000000004\nexpected y coordinate of No.2 local vertex: 0.8660254037844384\n\n");
        //test getXc and getYc
        System.out.print("\nXc: "+shape.getXc());
        System.out.print("\nexpected Xc: 10.0\n");
        System.out.print("\nYc: "+shape.getYc());
        System.out.print("\nexpected Yc: 10.0\n");
        //rotation
        shape.rotate(130);
        System.out.print("test rotation: ");
        System.out.print(shape.getTheta());
        System.out.print("\nexpected rotation: 1130.0\n\n");
        //translation
        System.out.print("test translation: ");
        shape.translate(19.7,20.3);
        System.out.print(shape.getXc());
        System.out.print(" ");
        System.out.print(shape.getYc());
        System.out.print("\nexpected translation: 29.7 30.3\n\n");

        //test the getX method
        int[] xCanvas= shape.getX();
        System.out.print("Canvas coordinate of X: ");
        for (int x: xCanvas){ //similar to local vertices array above, Canvas array are also printed to compare with the expected output. 
            System.out.format("%d  ",x);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of X: 30  30  30\n");
        //test the getY method
        int[] yCanvas= shape.getY();
        System.out.print("Canvas coordinate of Y: ");
        for (int y: yCanvas){
            System.out.format("%d  ", y);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of Y: 30 30 30\n");
    }
}
