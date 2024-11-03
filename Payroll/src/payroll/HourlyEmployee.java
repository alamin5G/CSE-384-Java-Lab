
package payroll;

/**
 *
 * @author alami
 */

public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double amount, double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber, amount);
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public double earningAmount() {
        return wage * hours + getAmount();
    }

    @Override
    public String toString() {
        return "Hourly Employee: " + getFirstName() + " " + getLastName() + ", SSN: " + getSocialSecurityNumber() + ", Salary: " + earningAmount();
    }
}
