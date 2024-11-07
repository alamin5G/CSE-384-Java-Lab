/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package two;

/**
 *
 * @author alami
 */

public class Circle extends Shape {
    private double radius;

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
        this.area = getArea();
    }

    // Method to calculate the area
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
