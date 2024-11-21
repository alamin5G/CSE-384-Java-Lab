/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly1;

/**
 *
 * @author alami
 */
public class Main {
    
    public static void main(String[] args) {
        Shape shape = new Rectangle(5, 6);
        
        System.out.println("Rectangle shape area : " + shape.getArea());
        System.out.println("Rectangle : " + shape.toString());
        

        Shape triangle = new Triangle(5, 6);
        
        System.out.println("Rectangle shape area : " + triangle.getArea());
        System.out.println("Rectangle : " + triangle.toString());
        
    }
    
}
