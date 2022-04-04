package exams;

import java.util.Scanner;

public class ThroneConquering {
    static int rows;
    static int cols;
    static int helenRows;
    static int helenCols;
    static int energy = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energyAdd = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());
        // square matrix

        energy = energy + energyAdd;
        char[][] matrix = new char[size][size];


        for (int r = 0; r < size; r++) {
            //прочитам всеки ред
            String line = scanner.nextLine();
            //превръщам си я в масив от char
            matrix[r] = line.toCharArray();

            if (line.contains("P")){
                rows = r;
                cols = line.indexOf("P");


            }

            if (line.contains("H")){
                helenRows = r;
                helenCols = line.indexOf("H");


            }

            }



        while (!(matrix[rows][cols] == matrix[helenRows][helenCols]) && !(energy <= 0)){
            String[] commands = scanner.nextLine().split("\\s+");

        int enemyRow = Integer.parseInt(commands[1]);
        int enemyCol = Integer.parseInt(commands[2]);

            matrix[enemyRow][enemyCol] = 'S';

            String directionParis = commands[0];

            if (directionParis.equals("up")) {
                moveParis(matrix, rows - 1, cols);
            } else if (directionParis.equals("down")) {
                moveParis(matrix, rows + 1, cols);
            } else if (directionParis.equals("left")) {
                moveParis(matrix, rows, cols - 1);
            } else if (directionParis.equals("right")) {
                moveParis(matrix, rows, cols + 1);
            }

        }

        if (energy <= 0) {
            System.out.println("Paris died at " + rows + ";" + cols + ".");
        } else if (matrix[rows][cols] == matrix[helenRows][helenCols]){
            System.out.println("Paris has successfully abducted Helen! Energy left: " + energy);
        }

        printMatrix(matrix);
        }

    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;

    }
    private static void moveParis(char[][] matrix, int newRow, int newCol) { // горе в логиката си задавам посока (moveMouse(matrix, mouseRow + 1, mouseCol) на долу примерно).
        //Moving UP
        //int newRow = mouseRow -1;
        // int newCol = mouseCol;
        //сетвам ги като параметри на метода

        //проверка за валиден индекс
   if (isOutOfBounds(matrix, newCol, newRow)){
       energy --;
       return; // излизам от метода, не обновявам индекците, НЕ МЪРДА
   } else if (matrix[newRow][newCol] == 'S'){
       energy -= 2;
      // matrix[newRow][newCol] ='P';
   } else if (matrix[newRow][newCol] == 'H'){
       energy--;
       matrix[rows][cols] = '-';
       matrix[helenRows][helenCols] = '-';

       return;
   }

   if (energy <= 0){
       matrix[rows][cols] = '-';

       matrix[newRow][newCol] = 'X';

       cols = newRow;
       rows = newCol;

   } else {
       energy--;
      matrix[rows][cols] = '-';

       matrix[newRow][newCol] = 'P';

       cols = newRow;
       rows = newCol;
   }
    }
    private static void printMatrix(char[][] matrix) { // квадратна матрица
        for (char[] arr : matrix) { //обхождам всеки масив от матрицата // всеки ред
            for (char c : arr) { // за всеки елемент от дадения масив (ред) // всеки елемент
                System.out.print(c);
            }
            System.out.println(); // преминавам на нов ред
        }
    }



    }





