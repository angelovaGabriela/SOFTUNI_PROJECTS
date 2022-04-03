package exams;

import java.util.Scanner;

public class MouseAndCheese {
   public static int food = 0;
   public static int mouseRow = 0; //редове на мишката
   public static int mouseCol = 0; //колони на мишката

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //The size of the square matrix
        int sizeMatrix = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[sizeMatrix][sizeMatrix]; // Матрица от чарове, защото няма разтояние, един стринг е

        for (int rows = 0; rows < sizeMatrix; rows++) { //обхождам редовете на матрицата
            //получавам какво има на реда - "M--"
            String line = scanner.nextLine();
            //превръщам полученото в charArray, за да мога да достъпвам елементи (символи)
            matrix[rows] = line.toCharArray();

            if (line.contains("M")) { // МИШКАТА Е НАМЕРЕНА
                //МЯСТО НА МИШКАТА
                mouseRow = rows; // текущия ред
                mouseCol = line.indexOf("M");// индексът от реда, на който съм намерила мишката

            }


        }
            //слагам брейк пойнт тук,
            //пускам първия вход,
            //проверявам дали съм прочела правилно данните
            //и къде е мишката
            //System.out.println();

          // получавам команда, която казва на къде да ходя
            String command = scanner.nextLine();
            while(!command.equals("end")){
                switch (command){
                    case "up":
                        moveMouse(matrix, mouseRow-1, mouseCol);
                        break;
                    case "down":
                        moveMouse(matrix, mouseRow + 1, mouseCol);
                        break;
                    case "left":
                        moveMouse(matrix,mouseRow, mouseCol - 1);
                        break;
                    case "right":
                        moveMouse(matrix, mouseRow, mouseCol + 1);
                        break;
                }
                if (isOutOfBounds(matrix, mouseRow, mouseCol)){
                    break; // ако мишката е излязла програмата спира
                }


                command = scanner.nextLine(); // получавам нова команда, за да не е безкраен цикъла
            }

            //Количеството изядена храна трябва да е минимум 5

            if (food < 5 ){
                int cheeseLeft = 5 - food; // останала храна
                System.out.println("The mouse couldn't eat the cheeses, she needed " + cheeseLeft + " cheeses more.");
            } else {
                System.out.println("Great job, the mouse is fed " + food + " cheeses!");
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

    // метод за местене на животно
    private static void moveMouse(char[][] matrix, int newRow, int newCol) {
       //Moving UP
       //int newRow = mouseRow -1;
       // int newCol = mouseCol;
       //сетвам ги като параметри на метода

        //проверка за валиден индекс
        if (isOutOfBounds(matrix, newRow, newCol)){ // задавам матрицата с новите индекси след местенето
            System.out.println("Where is the mouse?");
            // след като новите индекси са outOfBounds взимам предишните и ги замествам с '-' (empty space)
            matrix[mouseRow][mouseCol] = '-';
            return; // излизам от метода
        }

        //гледам какво има на новите на реда и обхождам случаите cheese / bonus / "-" - empty space

            if (matrix[newRow][newCol] == 'c') { //cheese
                food++; // изяжда храната и добавям 1
                matrix[newRow][newCol] = '-';
            } else if (matrix[newRow][newCol] == 'B') { // bonus
                // the bonus disappears
                matrix[newRow][newCol] = '-';
                // mouse gets the a bonus - one move forward
             matrix[newRow][newCol]++;

            }



        matrix[mouseRow][mouseCol] = '-'; // замествам старото място на мишката с '-'

        matrix[newRow][newCol] = 'M'; // слагам мишката на новото й място

        mouseRow = newRow; // ъпдейтвам индексите й
        mouseCol = newCol;
    }

    //метод, който проверява за валиден индекс
    private static boolean isOutOfBounds(char[][] matrix, int r, int c) {
        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[r].length;

    }
    //22/100 - да я погледна




}
