
package lab_report;

/**
 *
 * @author alami
 */
public class Triangle extends Shape {

    public Triangle( double height, double length) {
        super(height, length);
    }

    @Override
    public double area() {
        return 0.5 * height * length;
    }

  
}
