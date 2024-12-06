/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eleven;

/**
 *
 * @author alami
 */
// Base class
class Laptop {
    // Common properties
    protected String name;
    protected double price;
    protected String processor;
    protected String ram;
    protected String hardDrive;

    // Constructor for initializing properties
    public Laptop(String name, double price, String processor, String ram, String hardDrive) {
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.ram = ram;
        this.hardDrive = hardDrive;
    }

    // Method to display laptop details
    public void printDetails() {
        System.out.println("Name       : " + name);
        System.out.println("Price      : $" + price);
        System.out.println("Processor  : " + processor);
        System.out.println("Ram        : " + ram);
        System.out.println("HDD        : " + hardDrive);
        System.out.println("*************************************");
    }
}

// Derived classes for specific laptops
class Lenovo extends Laptop {
    public Lenovo() {
        super("Lenovo", 1000, "i3", "2GB", "500GB");
    }
}

class Dell extends Laptop {
    public Dell() {
        super("Dell", 1500, "i5", "4GB", "1TB");
    }
}

class Sony extends Laptop {
    public Sony() {
        super("Sony", 2000, "i9", "8GB", "1TB");
    }
}

// Main class
public class LaptopDetails {
    public static void main(String[] args) {
        // Create objects for each laptop model
        Lenovo lenovo = new Lenovo();
        Dell dell = new Dell();
        Sony sony = new Sony();

        // Print details of each laptop
        lenovo.printDetails();
        dell.printDetails();
        sony.printDetails();
    }
}
