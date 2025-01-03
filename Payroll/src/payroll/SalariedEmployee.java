
package payroll;

/**
 *
 * @author alami
 */

public class SalariedEmployee extends Employee {
    private double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double amount, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber, amount);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double earningAmount() {
        return weeklySalary * 4 + super.earningAmount();
    }

    @Override
    public String toString() {
        return "Salaried Employee: " + super.toString() ;
    }
}
