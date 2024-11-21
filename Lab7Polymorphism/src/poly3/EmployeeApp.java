/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly3;

/**
 *
 * @author alami
 */
public class EmployeeApp {
    
    public static void main(String[] args) {
        Employee contract = new ContractEmployee(200, 240, "Md. Alamin");
        contract.display();
        contract.earning();
        System.out.println("---------------------------");
        Employee permanent = new PermanentSalary(3, 50000, "Rakib Mia");
        permanent.display();
        permanent.earning();
    }
}
