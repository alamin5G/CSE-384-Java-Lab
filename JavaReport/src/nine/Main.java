// Abstract class Employee
abstract class Employee {
    protected String name;
    protected double baseSalary;

    // Constructor
    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();

    // Display employee details
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Total Salary: " + calculateSalary());
    }
}

// Manager class
class Manager extends Employee {
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}

// Programmer class
class Programmer extends Employee {
    private double overtimePay;

    public Programmer(String name, double baseSalary, double overtimePay) {
        super(name, baseSalary);
        this.overtimePay = overtimePay;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + overtimePay;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create Manager and Programmer objects
        Manager manager = new Manager("Alice", 50000, 10000);
        Programmer programmer = new Programmer("Bob", 40000, 5000);

        // Display their details
        System.out.println("Manager Details:");
        manager.displayDetails();

        System.out.println("\nProgrammer Details:");
        programmer.displayDetails();
    }
}
