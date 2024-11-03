
package payroll;

/**
 *
 * @author alami
 */
public class PayrollSystemTest {
    public static void main(String[] args) {
        SalariedEmployee salariedEmployee = new SalariedEmployee("Md", "Alamin", "123-45-6789", 500, 1000);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Noorjahan", "Easha", "987-65-4321", 300, 20, 40);

        System.out.println(salariedEmployee);
        System.out.println("Salary: " + salariedEmployee.earningAmount());

        System.out.println(hourlyEmployee);
        System.out.println("Salary: " + hourlyEmployee.earningAmount());
    }
}

