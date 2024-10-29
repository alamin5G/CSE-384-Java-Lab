
package lab_report;

/**
 *
 * @author alami
 */
public abstract class Shape {
    protected double height;
    protected double length;

    public Shape(double height, double length) {
        this.height = height;
        this.length = length;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // Abstract method area
    public abstract double area();

    @Override
    public String toString() {
        return "Shape["+ "height=" + height + ", length=" + length + "]";
    }
}

