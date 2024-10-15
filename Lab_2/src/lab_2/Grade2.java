package lab_2;

/**
 *
 * @author alami
 */
import java.util.Scanner;

class Grade2 {

    public static void main(String[] args) {
        int number;
        long startTime, endTime, elapsedTime;

        Scanner input = new Scanner(System.in);
        number = input.nextInt();

        startTime = System.nanoTime();
        /*
        if (number <= 100 & number >= 90) { 
            System.out.println("A");
        }

        if (number >= 80 & number < 90) {
            System.out.println("B");
        }

        if (number >= 70 & number < 80) {
            System.out.println("C");
        }

        if (number >= 60 & number < 70) {
            System.out.println("D");
        }

        if (number < 60) {
            System.out.println("F");
        }
*/
        
        //modified code and reduced the operators;
        
        char grade = (number >= 90) ? 'A' : (number >= 80) ? 'B' :
                (number >= 70) ? 'C' : (number >= 60) ? 'D' : 'F';
        
                     
        System.out.println(grade);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time=" + elapsedTime);
    }
}
