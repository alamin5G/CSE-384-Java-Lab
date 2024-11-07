
package two;

/**
 *
 * @author alami
 */

public class Rectangle extends Shape {
    
    private double length;
    private double width;

    // Constructor
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        this.area = getArea();
    }

    // Method to calculate the area
    @Override
    public double getArea() {
        return length * width;
    }
}
