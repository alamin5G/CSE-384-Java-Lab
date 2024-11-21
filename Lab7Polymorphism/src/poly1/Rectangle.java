/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly1;

/**
 *
 * @author alami
 */
public class Rectangle implements Shape {

    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double getArea() {
       return length * width;
    }
    
    @Override
    public String toString(){
        return "The area of rectangle is : " + getArea() + ", and Shape color is: "+ color ;
    }
    
}
