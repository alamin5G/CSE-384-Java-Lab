/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly3;

/**
 *
 * @author alami
 */
abstract class Employee {
    private String empName;

    public Employee(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    public void display()
    {
        System.out.println("Employee name: " + empName);
    }
    public abstract void earning();
}
