
package payroll;

/**
 *
 * @author alami
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private final double amount;

    public Employee(String firstName, String lastName, String socialSecurityNumber, double amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.amount = amount;
    }

    public double earningAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + ", SSN: " + socialSecurityNumber + ", Salary: " + amount;
    }
}

