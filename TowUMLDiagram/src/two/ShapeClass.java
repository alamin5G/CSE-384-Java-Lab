/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package two;

/**
 *
 * @author alami
 */
public class ShapeClass {

    public static void main(String[] args) {
        
        Rectangle rectangle = new Rectangle(5, 3);
        System.out.println("Rectangle Area: " + rectangle.getArea());

        Circle circle = new Circle(2.5);
        System.out.println("Circle Area: " + circle.getArea());

    }

}
