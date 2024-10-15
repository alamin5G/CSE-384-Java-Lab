package lab_2;

/**
 *
 * @author alami
 */
import java.util.Scanner;

class Grade1 {

    public static void main(String[] args) {
        int number;
        long startTime, endTime, elapsedTime;

        Scanner input = new Scanner(System.in);
        number = input.nextInt();

        startTime = System.nanoTime();

        switch (number / 10) {
            case 10: // for numbers 100
            case 9:  // for numbers 90-99
                System.out.println("A");
                break;
            case 8:  // for numbers 80-89
                System.out.println("B");
                break;
            case 7:  // for numbers 70-79
                System.out.println("C");
                break;
            case 6:  // for numbers 60-69
                System.out.println("D");
                break;
            default: // for numbers below 60
                System.out.println("F");

        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time=" + elapsedTime);
    }
}
