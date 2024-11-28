/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly1;

/**
 *
 * @author alami
 */
public class Triangle implements Shape {

    private int base;
    private int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }
    
    
    @Override
    public double getArea() {
       return (base*height)/2;
    }
    
     @Override
    public String toString(){
        return "The area of triangle is : " + getArea() + ", and Shape color is: "+ color ;
    }
    
}
