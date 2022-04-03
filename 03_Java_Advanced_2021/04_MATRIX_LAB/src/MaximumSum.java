import java.util.Arrays;
import java.util.Scanner;

public class MaximumSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

int rows = Integer.parseInt(scanner.nextLine().split(", ")[0]); // редовете


        int [][] matrix =  readMatrix(rows, scanner,", ");// прочитаме матрицата от колоната

        int maxSum = 0;
        int [][]maxMatrix = new int [2][2];
        for (int r = 0; r < matrix.length -1; r++) {// rows = number of elements in the matrix (the number of the arrays)
            for (int c = 0; c < matrix[r].length-1; c++) { // cols = number of cols on the current line

                int current = matrix[r][c];
                int intRight = matrix[r][c+1];
                int intBottom = matrix[r+1][c];
                int leftRight = matrix[r+1][c+1];

                int currentSum = current+ intRight + intBottom + leftRight;

                if (maxSum < currentSum){
                    maxSum = currentSum;

                    maxMatrix = new int[][] {
                            {current, intRight},
                            {intBottom, leftRight}
                    };
                }
            }
        }

        printMatrix(maxMatrix);
        System.out.print(maxSum);

    }

    private static void printMatrix(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static int[][] readMatrix(int rows, Scanner scanner,String splitPattern) {
        int [][] matrix = new int[rows][];

        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine()
                    .split(splitPattern))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return matrix;
    }
}
