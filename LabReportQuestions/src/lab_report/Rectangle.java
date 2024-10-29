package lab_report;

/**
 *
 * @author alami
 */
public class Rectangle extends Shape {

    public Rectangle(double height, double length) {
        super(height, length);
    }

    @Override
    public double area() {
        return height * length;
    }

}

