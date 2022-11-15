import java.awt.Color;
/**
 * this class is to test the triangle class.
 * @author Herman Chung
 */
public class TriangleTester {
    public static void main(String[] args){
        //here example data is used to demonstrate and test a square
        Triangle triangle = new Triangle();
		triangle.color = new Color(0, 0, 250);
		triangle.filled = true;
		triangle.theta = -Math.PI / 2;
		triangle.xc = 12.4;
		triangle.yc = 23.7;
        triangle.xLocal=new double[3];
        triangle.yLocal=new double[3]; //instead of using the numbers in GUI, other numbers are used
        triangle.setVertices(-24.72);
        //trial run the methods in triangle class
        triangle.rotate(130);
        System.out.print("test rotation: ");
        System.out.print(triangle.theta);
        System.out.print("\nexpected rotation: 128.4292036732051\n\n");
        //translation
        System.out.print("test translation: ");
        triangle.translate(19.7,20.3);
        System.out.print(triangle.xc);
        System.out.print(" ");
        System.out.print(triangle.yc);
        System.out.print("\nexpected translation: 32.1 44.0\n\n");
		

        System.out.print("local coordinate of x: "); 
        for (int i = 0; i<3; i++){ //in this loop we run the local vertice array generate by the program and print it out to compare with the expected output.
            System.out.print(triangle.xLocal[i]);
            System.out.print(' '); 
        }
        System.out.print("\nexpected local coordinate of x: 24.72 -12.360000000000003 -12.360000000000003 \n");
    
        int[] xCanvas = triangle.getX();
        System.out.print("Canvas coordinate of X: ");
        for (int x: xCanvas){ //similar to local vertices array above, Canvas array are also printed to compare with the expected output. 
            System.out.format("%d  ",x);
        }
        System.out.println();
        System.out.println("expected Canvas coordinate of X: 9  51  36\n");
        //then the same functions are done by arrays of y
        System.out.print("local coordinate of y: ");
        for (int i = 0; i<3; i++){
            System.out.print(triangle.yLocal[i]);
            System.out.print(' ');
        }
        System.out.print("\nexpected local coordinate of y: 0.0 -21.40814798155132 21.40814798155132 \n");
        
        int[] yCanvas = triangle.getY();
        System.out.print("Canvas coor of Y: ");
        for (int y: yCanvas){
            System.out.format("%d  ", y);
        }
        System.out.println();
        System.out.println("expected Canvas coor of Y: 53  59  20\n");
    }
}
