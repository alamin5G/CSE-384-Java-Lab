/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ten;

/**
 *
 * @author alami
 */
// Base class
abstract class Kitchen {
    // Common method to use the kitchen
    public void useKitchen() {
        System.out.println("Using the shared kitchen...");
    }

    // Abstract method for preparation
    public abstract void prepareFood();
}

// Derived class for "You"
class You extends Kitchen {
    @Override
    public void prepareFood() {
        System.out.println("Preparing bread and butter for breakfast.");
    }
}

// Derived class for "Mother"
class Mother extends Kitchen {
    @Override
    public void prepareFood() {
        System.out.println("Preparing bread, vegetables, and tea for breakfast.");
    }
}

// Derived class for "Sister"
class Sister extends Kitchen {
    @Override
    public void prepareFood() {
        System.out.println("Preparing noodles and mango juice for breakfast.");
    }
}

// Main class
public class FamilyKitchen {
    public static void main(String[] args) {
        // Create objects for each family member
        Kitchen you = new You();
        Kitchen mother = new Mother();
        Kitchen sister = new Sister();

        // Each member uses the kitchen
        System.out.println("You:");
        you.useKitchen();
        you.prepareFood();

        System.out.println("\nMother:");
        mother.useKitchen();
        mother.prepareFood();

        System.out.println("\nSister:");
        sister.useKitchen();
        sister.prepareFood();
    }
}

