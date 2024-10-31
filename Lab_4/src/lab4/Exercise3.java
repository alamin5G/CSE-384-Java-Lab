
package lab4;

/**
 *
 * @author alami
 */
public class Exercise3 {
   public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrix2 = {
            {1, 2},
            {2, 0},
            {3, 1}
        };

        //matrix1 row and matrix2 colum; initialized result 2d array;
        int[][] result = new int[matrix1.length][matrix2[0].length];

        // Multiply 
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        // Print the result
        System.out.println("Resulting Matrix:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
