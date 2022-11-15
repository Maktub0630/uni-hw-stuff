import java.awt.Color;
/**
 * this class is to test shape class
 * @author Herman Chung 
 */
public class ShapeTester {
    public static void main(String[] args){
        //initialize random data
        Shape shape =new Shape();
        shape.color = new Color(200, 100, 0);;
        shape.filled = true;
        shape.theta =130.0;
        shape.xc=20.7;
        shape.yc=14;
        shape.xLocal = new double[2]; //here only two coordinates are included because a "stick" shape will be used for test
        shape.yLocal = new double[2];
        shape.xLocal[0] = 1;
        shape.xLocal[1] = 0;
        shape.yLocal[0] = 0;
        shape.xLocal[1] = 1;
        //trial run the methods in Shape class
        shape.rotate(130);
        System.out.print("test rotation: ");
        System.out.print(shape.theta);
        System.out.print("\nexpected rotation: 260.0\n\n");
        //translation
        System.out.print("test translation: ");
        shape.translate(19.7,20.3);
        System.out.print(shape.xc);
        System.out.print(" ");
        System.out.print(shape.yc);
        System.out.print("\nexpected translation: 40.4 34.3\n\n");
        shape.setVertices(47.234);
        //testing setVertices X(although not required)
        System.out.print("local coordinate of x: "); 
        for (int i = 0; i<2; i++){ //in this loop we run the local vertice array generate by the program and print it out to compare with the expected output.
            System.out.print(shape.xLocal[i]);
            System.out.print(' '); 
        }
        System.out.print("\nexpected local coordinate of x:  1.0 1.0 \n");
        //test the getX method
        int[] xCanvas= shape.getX();
        System.out.print("Canvas coordinate of X: ");
        for (int x: xCanvas){ //similar to local vertices array above, Canvas array are also printed to compare with the expected output. 
            System.out.format("%d  ",x);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of X: 40  40\n");
        //testing setVertices Y(although not required)
        System.out.print("local coordinate of y: ");
        for (int i = 0; i<2; i++){
            System.out.print(shape.yLocal[i]);
            System.out.print(' ');
        }
        System.out.print("\nexpected local coordinate of y: 0.0 0.0 \n");
        //test the getY method
        int[] yCanvas= shape.getY();
        System.out.print("Canvas coordinate of Y: ");
        for (int y: yCanvas){
            System.out.format("%d  ", y);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of Y: 35  35\n");
        
        
    }
    
    
}
