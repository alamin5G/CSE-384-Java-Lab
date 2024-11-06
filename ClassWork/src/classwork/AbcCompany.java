
package classwork;

/**
 *
 * @author alami
 */
// Base class for Employee
abstract class Employee {
    protected String name;
    protected int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

// Manager class that extends Employee
class Manager extends Employee {
    private final double baseSalary;
    private final double bonus;

    public Manager(String name, int id, double baseSalary, double bonus) {
        super(name, id);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
}

// Programmer class that extends Employee
class Programmer extends Employee {
    private final double baseSalary;
    private final double overtimePay;
    private final int overtimeHours;

    public Programmer(String name, int id, double baseSalary, double overtimePay, int overtimeHours) {
        super(name, id);
        this.baseSalary = baseSalary;
        this.overtimePay = overtimePay;
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (overtimePay * overtimeHours);
    }
}

public class AbcCompany {
    public static void main(String[] args) {
        Employee manager = new Manager("Alice", 101, 5000, 1000);
        Employee programmer = new Programmer("Bob", 102, 4000, 50, 20);

        System.out.println(manager.getName() + "'s Salary: $" + manager.calculateSalary());
        System.out.println(programmer.getName() + "'s Salary: $" + programmer.calculateSalary());
    }
}

