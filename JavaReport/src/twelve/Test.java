/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package twelve;

/**
 *
 * @author alami
 */
class Animal {
    // Fields
    private int height;
    private float weight;

    // Method
    public void walk() {
        System.out.println("I walk on the street");
    }

   
}

class Bird extends Animal {
    // Constructor for Bird (Part d)
    public Bird() {
        System.out.println("Bird object created");
    }
}

interface FlyingBird {
    // Default method with implementation
    default void fly() {
        System.out.println("I fly in the sky");
    }
}

class Parrot extends Bird implements FlyingBird {
    // Static method (Part g)
    public static void display() {
        System.out.println("I am Mithu!");
    }
}

public class Test {
    public static void main(String[] args) {
        // Create a Parrot object
        Parrot parrot = new Parrot();

        // Call static method
        Parrot.display();

        // Call inherited methods
        parrot.walk();   // From Animal class
        parrot.fly();    // From FlyingBird interface
    }
}


