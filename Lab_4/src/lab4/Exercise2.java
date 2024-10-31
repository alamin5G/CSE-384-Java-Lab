package lab4;


/**
 *
 * @author alami
 */
public class Exercise2 {

    public static void main(String[] args) {
        int[][] array1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] array2 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        int[][] result = new int[array1.length][array1[0].length];

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                result[i][j] = array1[i][j] + array2[i][j];
            }
        }

        System.out.println("Resulting Array:");
        sum(result); //invoking the sum method;
    }

    private static void sum(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
