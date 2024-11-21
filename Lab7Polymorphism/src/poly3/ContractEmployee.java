/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly3;

/**
 *
 * @author alami
 */
public class ContractEmployee extends Employee{
    
    private double wage;
    private double hours;

    public ContractEmployee(double wage, double hours, String empName) {
        super(empName);
        this.wage = wage;
        this.hours = hours;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public void earning() {
        System.out.println("Earnings of the Contract Employee: " + wage * hours);
    }

   
    
}
