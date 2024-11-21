/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly2;

/**
 *
 * @author alami
 */
// Main class
public class EnclosureAnimalExample {
    public static void main(String[] args) {
        
        Pig pig = new Pig("Piggy");
        Cow cow = new Cow("MooMoo");

        
        System.out.println("Descriptions:");
        pig.eat();
        pig.describeEnclosure();
        pig.makeSound();
        pig.sleep();
        System.out.println("-----------------------");
        cow.eat();
        cow.describeEnclosure();
        cow.makeSound();
        cow.sleep();
        

    }
}
