/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly2;

/**
 *
 * @author alami
 */
abstract class Animal extends Enclosure {
    public Animal(String name) {
        super(name);
    }

    // Abstract method for sound
    public abstract void makeSound();
    
    public abstract void eat();
    
    public abstract void sleep();
}
