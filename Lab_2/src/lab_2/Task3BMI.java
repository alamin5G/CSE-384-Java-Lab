package lab_2;

/**
 *
 * @author alami
 */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Task3BMI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the weight in kilogram: ");
        double weight = input.nextDouble();
        System.out.print("Enter the height in meters: ");
        double height = input.nextDouble();


        double bmi = weight / (height * height);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.print("BMI is " + df.format(bmi));

        if(bmi < 16){
            System.out.println("seriously underweight ");
        }else if (bmi >= 16 && bmi <= 18) {
            System.out.println("underweight");
        }else if (bmi > 18 && bmi <= 24) {
            System.out.println("normal weight");
        }else if (bmi > 24 && bmi <= 29) {
            System.out.println("overweight");
        }else if (bmi > 29 && bmi <= 35) {
            System.out.println("seriously overweight");
        }else{
            System.out.println("gravely overweight");
        }
    }
}