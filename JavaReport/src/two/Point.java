
package two;

/**
 *
 * @author Student
 */
public class Point {
    int x;
    int y; 

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}

class Circle extends Point{
    
    double radius;
    
    public Circle(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }
    
    public double getArea(){
        return (3.1416)*radius*radius;
    }
    
    public double getCircumference(){
         return 2 * (3.1416) * radius;
    }

    @Override
    public String toString() {
        return "Circle[center=("+x +"," + y+");" + "radius=" + radius + ']';
    }

    
    public static void main(String[] args) {
        Circle c1 = new Circle(75, 20, 70);
        System.out.println(c1);
        System.out.println("Area of the Circle 1: "+ c1.getArea());
        System.out.println("Circumference of the Circle 1: "+ c1.getCircumference());
        
    }
    
    
}
