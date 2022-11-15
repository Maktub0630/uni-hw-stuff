import java.awt.Color;
/**
 * this class is to test drive the circle class.
 * @author Herman Chung
 */

public class CircleTester {
    public static void main(String[] args){
        //input random data
        Circle circle = new Circle();
		circle.color = new Color(0, 250, 0);
		circle.filled = true;
		circle.theta = 0;
		circle.xc = 90.9;
		circle.yc = 13.72;
		
        //trial run the methods in circle class
        circle.rotate(130);
        System.out.print("test rotation: ");
        System.out.print(circle.theta);
        System.out.print("\nexpected rotation: 130.0\n\n");
        //translation
        System.out.print("test translation: ");
        circle.translate(19.7,20.3);
        System.out.print(circle.xc);
        System.out.print(" ");
        System.out.print(circle.yc);
        System.out.print("\nexpected translation: 110.60000000000001 34.02\n\n");

		circle.setVertices(47.234);
        System.out.print("local coordinate of x: "); 
        for (int i = 0; i<2; i++){ //in this loop we run the local vertice array generate by the program and print it out to compare with the expected output.
            System.out.print(circle.xLocal[i]);
            System.out.print(' '); 
        }
        System.out.print("\nexpected local coordinate of x:  -47.234 47.234 \n");

        int[] xCanvas = circle.getX();
        System.out.print("Canvas coordinate of X: ");
        for (int x: xCanvas){ //similar to local vertices array above, Canvas array are also printed to compare with the expected output. 
            System.out.format("%d  ",x);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of X: 56  150\n");
        //then the same functions are done by arrays of y
        System.out.print("local coordinate of y: ");
        for (int i = 0; i<2; i++){
            System.out.print(circle.yLocal[i]);
            System.out.print(' ');
        }
        System.out.print("\nexpected local coordinate of y: -47.234 47.234 \n");

        int[] yCanvas = circle.getY();
        System.out.print("Canvas coor of Y: ");
        for (int y: yCanvas){
            System.out.format("%d  ", y);
        }
        System.out.println();
        System.out.println("expected Canvas coor of Y: -11  84\n");

    }
    
}
