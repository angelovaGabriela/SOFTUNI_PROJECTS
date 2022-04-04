import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalOfASquareMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int [][] matrix = readMatrix(size, scanner, "\\s+");

        int row = 0;
        int col = 0;
        while (row < size && col < size){ // първия диагонал
            int current = matrix[row][col];
            System.out.print(current + " ");
            row++; // увеличаваме реда и колоната с 1 за да премине напред
            col++;
        }

        System.out.println();

        row = size - 1;
        col = 0;

        while (row >= 0 && col < size){
            int current = matrix[row][col];
            System.out.print(current + " ");

            row--;
            col++;
        }


       // printMatrix();
        
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
