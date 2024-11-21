/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly3;

/**
 *
 * @author alami
 */
public class PermanentSalary extends Employee{
    
    private int gradeSalary;
    private double salary;

    public PermanentSalary(int gradeSalary, double salary, String empName) {
        super(empName);
        this.gradeSalary = gradeSalary;
        this.salary = salary;
    }

    public int getGradeSalary() {
        return gradeSalary;
    }

    public void setGradeSalary(int gradeSalary) {
        this.gradeSalary = gradeSalary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    

    @Override
    public void earning() {
        System.out.println("Permanent Employee Salary: " + salary);
    }
    
}
