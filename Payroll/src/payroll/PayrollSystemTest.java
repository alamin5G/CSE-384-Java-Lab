
package payroll;

/**
 *
 * @author alami
 */
public class PayrollSystemTest {
    public static void main(String[] args) {
        SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Doe", "123-45-6789", 500, 1000);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Jane", "Smith", "987-65-4321", 300, 20, 40);

        System.out.println(salariedEmployee);
        System.out.println("Salary: " + salariedEmployee.earningAmount());

        System.out.println(hourlyEmployee);
        System.out.println("Salary: " + hourlyEmployee.earningAmount());
    }
}

