package java_advanced_training;

import java.util.Scanner;
public class FormulaOne {
    public static int playerRows = 0;
    public static int playerCols = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine()); // квадратната матрица
        int countCommands = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][size];

        for (int rows = 0; rows < size; rows++) { // for цикъл по редовете, квадратна матрица
            //получавам какво има на реда - 1. "..."// 2. ".P..."
            String line = scanner.nextLine();
            //превръщам полученото в charArray
            matrix[rows] = line.toCharArray();

            if (line.contains("P")) { // Състезателя е намерен
                //Място на състезателя
                playerRows = rows;
                playerCols = line.indexOf("P");// индексът, на който съм намерила състезателя

            }
        }

        //слагам брейк пойнт тук,
        //пускам първия вход,
        //проверявам дали съм прочела правилно данните
        // и къде е състезателя
        //System.out.println();


        // получавам команда, която казва на къде да ходя
        //продължавам докато командите не свършат
        String command = scanner.nextLine();
        while (countCommands > 0) {
            switch (command) {
                case "up":
                    movePlayer(matrix, playerRows - 1, playerCols);
                    break;
                case "down":
                    movePlayer(matrix, playerRows + 1, playerCols);
                    break;
                case "left":
                    movePlayer(matrix, playerRows, playerCols - 1);
                    break;
                case "right":
                    movePlayer(matrix, playerRows, playerCols + 1);
                    break;
            }


            countCommands--; // намалявам броя на командите
            if (countCommands > 0 ) {
                command = scanner.nextLine(); // получавам нова команда, за да не е безкраен цикъла
            } else if (countCommands == 0 && !isWon(matrix)){
                System.out.println("Oh no, the player got lost on the track!");
                 break;
            } else if (countCommands == 0 && isWon(matrix)){
                System.out.println("Well done, the player won first place!");
            }

        }

        printMatrix(matrix);


    }

    private static void printMatrix(char[][] matrix) { // квадратна матрица
        for (char[] arr : matrix) {
            for (char c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void movePlayer(char[][] matrix, int newRow, int newCol) {

        if (isOutOfBounds(matrix, newRow, newCol)) { // задавам матрицата с новите индекси след местенето
            // след като новите индекси са outOfBounds взимам предишните и ги замествам с '.' (empty space)

            int [] newIndex = flipPlayer(matrix.length, newRow, newCol);
            newRow = newIndex[0];
            newCol = newIndex [1];
            // ако състезателя е излязъл от границите на матрицата
            // he comes in from the other side
            // if the player is on 0, 0 and the next command is left, he goes to the last spot on the first row

        }

        //обхождам си реда и търся traps / bonus / "F" - final line
        if (matrix[newRow][newCol] == 'B') {


           if (newRow == playerRows - 1) { // case - "up"

               newRow = playerRows - 2;
               newCol = playerCols;
           } else if (newRow == playerRows + 1) { // case - "down"
               newRow = playerRows + 2;
               newCol = playerCols;
           } else if (newCol == playerCols - 1) { // case - "left"
               newRow = playerRows;
               newCol = playerCols - 2;
           } else if (newCol == playerCols + 1) { // case - "right"
               newRow = playerRows;
               newCol = playerCols + 2;
           }


        } else if (matrix[newRow][newCol] == 'T') { // traps
            // move a step backwards // остава на същото място
            newRow = playerRows;
            newCol = playerCols;




        } else if (matrix[newRow][newCol] == 'F') { // final line
            // when he reaches the final line the game is over (program stops)
            isWon(matrix);

        }

        matrix[playerRows][playerCols] = '.'; // замествам старото място на състезателя с '.'

        matrix[newRow][newCol] = 'P'; // слагам състезателя на новото му място

        playerRows = newRow; // ъпдейтвам индексите му
        playerCols = newCol;


    }
    private static boolean isWon(char[][] matrix){
        for (char[] arr : matrix) {
            for (char c : arr) {
               if (c == 'F'){
                   return false; // не е стигнал финала
               }
            }

        }
        return true; // финиширал е
    }
    private static int[] flipPlayer(int length, int r, int c){
        int [] result = new int [2];

        if (r < 0){ // ако е излязъл от началото
            result[0] = length-1; // отива в края на реда
            result[1] = c;
        } else if (r >= length) { // ако е излязъл в края
            result[1] = c; // отива в началото на реда
        }
        return result;
    }


    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;
    }
}

//70/100
// https://pastebin.com/RDwSX6fC?fbclid=IwAR1NkzxsaqNf199pOM-Oemye_s4U6wMyGddGWGcXbP500lQ6sDf8UWPXt8c --> Решение от Деси
// https://github.com/nanko89/JavaAdvance/commit/7ea0d2e56f4d9fc0cdf252f6997c4500165b9cc5