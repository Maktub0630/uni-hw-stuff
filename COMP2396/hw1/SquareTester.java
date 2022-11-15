/**
 * this class is a tester class to test the square class.
 * @author Herman Chung
 */
import java.awt.Color;
public class SquareTester {
    public static void main(String[] args){
        //here example data is used to demonstrate and test a square
        Square square =new Square();
		square.color = new Color(25, 0, 0);
		square.filled = true;
		square.theta = 20;
		square.xc = 10;
		square.yc = 10;
        square.xLocal=new double[4];
        square.yLocal=new double[4];
        //trial run the method;
		 //instead of using the numbers in GUI, other numbers are used
        //trial run the methods in square class
        square.rotate(130);
        System.out.print("test rotation: ");
        System.out.print(square.theta);
        System.out.print("\nexpected rotation: 150.0\n\n");
        //translation
        System.out.print("test translation: ");
        square.translate(19.7,20.3);
        System.out.print(square.xc);
        System.out.print(" ");
        System.out.print(square.yc);
        System.out.print("\nexpected translation: 29.7 30.3\n\n");
        square.setVertices(1.1);

        System.out.print("local coordinate of x: "); 
        for (int i = 0; i<4; i++){ //in this loop we run the local vertice array generate by the program and print it out to compare with the expected output.
            System.out.print(square.xLocal[i]);
            System.out.print(' '); 
        }
        System.out.print("\nexpected local coordinate of x: 1.1 1.1 -1.1 -1.1 \n");
        //testing getX
        int[] xCanvas = square.getX();
        System.out.print("Canvas coordinate of X: ");
        for (int x: xCanvas){ //similar to local vertices array above, Canvas array are also printed to compare with the expected output. 
            System.out.format("%d  ",x);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of X: 31  30  28  30 \n");
        //then the same functions are done by arrays of y
        System.out.print("local coordinate of y: ");
        for (int i = 0; i<4; i++){
            System.out.print(square.yLocal[i]);
            System.out.print(' ');
        }
        System.out.print("\nexpected local coordinate of y: 1.1 -1.1 -1.1 1.1 \n");
        //testing getY
        int[] yCanvas = square.getY();
        System.out.print("Canvas coor of Y: ");
        for (int y: yCanvas){
            System.out.format("%d  ", y);
        }
        System.out.println();
        System.out.println("expected Canvas coor of Y: 30  29  30  32\n");
    }
}
