
import java.util.Scanner;

public class ComputeLoan {

    public static void main(String arg[]) {

        Scanner input = new Scanner(System.in);

        System.out.println("Scan AnualInterest Rate:");
        double anualInterestRate = input.nextDouble();

        double monthlyInterestRate = anualInterestRate / 1200;

        System.out.println("Scan Number of Year");
        double numberOfYears = input.nextDouble();

        System.out.println("Scan Total Loan Amount:");
        double loanAmount = input.nextDouble();

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        double totalYearlyPayment = monthlyPayment * numberOfYears * 12;

        System.out.println("The monthly payment is =" + (int) ((monthlyPayment * 100) / 100.0));
        System.out.println("The total payment is   =" + (int) ((totalYearlyPayment * 100) / 100.0));

    }

}
