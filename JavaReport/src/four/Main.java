/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package four;

/**
 *
 * @author alami
 */
abstract class A {
    // Abstract methods to set arrays
    public abstract void setArray1(int[][] array1);
    public abstract void setArray2(int[][] array2);
}

class C extends A {
    private int[][] matrix1;
    private int[][] matrix2;

    // Set the first matrix
    @Override
    public void setArray1(int[][] array1) {
        this.matrix1 = array1;
    }

    // Set the second matrix
    @Override
    public void setArray2(int[][] array2) {
        this.matrix2 = array2;
    }

    // Multiply the two matrices
    public int[][] multiply() {
        if (matrix1[0].length != matrix2.length) {
            throw new IllegalArgumentException("Matrix multiplication is not possible. Columns of the first matrix must equal rows of the second matrix.");
        }

        int rows1 = matrix1.length;
        int cols2 = matrix2[0].length;
        int commonDim = matrix1[0].length;

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < commonDim; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    // Display a matrix
    public void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();

        // Define two matrices
        int[][] mat1 = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] mat2 = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        // Set matrices in the object
        obj.setArray1(mat1);
        obj.setArray2(mat2);

        // Display matrices
        System.out.println("First Matrix:");
        obj.displayMatrix(mat1);

        System.out.println("Second Matrix:");
        obj.displayMatrix(mat2);

        // Multiply and display the result
        try {
            System.out.println("Result of Matrix Multiplication:");
            int[][] result = obj.multiply();
            obj.displayMatrix(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

