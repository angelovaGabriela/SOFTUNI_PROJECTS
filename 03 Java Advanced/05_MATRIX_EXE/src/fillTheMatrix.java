import java.util.Scanner;

public class fillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int n = Integer.parseInt(input.split(", ")[0]);
        String pattern = input.split(", ")[1];

        int [][] matrix = new int [n][n];

        if (pattern.equals("A")){
    fillMatrixPatternA(matrix);
} else if (pattern.equals("B")){
            fillMatrixPatternB(matrix);
        }
        printMatrix(matrix, n, n);
    }

    private static void fillMatrixPatternB(int [][] matrix) {
        // четна колона --> 0 - последен
        //нечетна ---> последен - 0
int startNumber = 1;
        for (int col = 0; col < matrix.length; col++) {
            if (col % 2 == 0){
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = startNumber++;
                }
            } else {
                for (int row = matrix.length - 1; row >= 0; row--) { // от последния към първия
                    matrix[row][col] = startNumber++;
                }
            }
        }
    }

    private static void fillMatrixPatternA(int [][] matrix) {
   int startNumber = 1;
        // обхождаме по колони
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = startNumber;// = startNumber ++ // първо си я използвам след това я увеличавам
                startNumber++;
            }
        }

    }


    public static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");// за всеки един от редовете отпечатай всяка колона
            }
            System.out.println();// следващия ред
        }
    }



}

