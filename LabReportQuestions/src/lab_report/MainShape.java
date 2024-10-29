
package lab_report;

/**
 *
 * @author alami
 */
public class MainShape {
 
    public static void main(String[] args) {
        
        Shape rectangle = new Rectangle(10, 5);
        Shape triangle = new Triangle(8, 6);

        System.out.println(rectangle);
        System.out.println("Area of Rectangle: " + rectangle.area());
      
        System.out.println("\n");
        
        System.out.println(triangle);
        System.out.println("Area of Triangle: " + triangle.area());
    }
}
