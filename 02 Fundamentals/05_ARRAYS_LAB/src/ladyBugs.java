import java.util.Arrays;
import java.util.Scanner;

public class ladyBugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fieldSize = Integer.parseInt(scanner.nextLine());//броя на кутийте в масива

        int[] field = new int[fieldSize];// масива

        int[] ladyBugsIndex = Arrays.
                stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();// заетите от бобулечки индекси ги разделям с място се пишат на конзолата

if(field.length > 0) {
    for (int ladyBugIndex : ladyBugsIndex) {//обхождам индексите и срещу всеки слагам 1
        if (ladyBugIndex >= 0 && ladyBugIndex <= field.length - 1) {
//само ако индекса е валиден
            field[ladyBugIndex] = 1;// във всеки един от индексите в масива LadyBugsIndex има по една гадинка
        }
    }
}
            String command = scanner.nextLine();

            while (!command.equals("end")) {

                // 0 right 1
                // 0 индекс от който се маха гадината
                // right посоката в която отлита
                // 1 с колко кутий напред в масива

                String[] tokens = command.split(" ");
                int startIndex = Integer.parseInt(tokens[0]);
                String direction = tokens[1];
                int lengthFly = Integer.parseInt(tokens[2]);
// мога да взема калинка от този индекс само ако там има калинка и той е валиден според големината на полето
                if (startIndex >= 0 && startIndex <= field.length - 1 && field[startIndex] == 1) {
                    field[startIndex] = 0;

                    if (direction.equals("right")) {
                        startIndex += lengthFly;

                        // ако следващия индекс е в полето и има калинка тя продължава да лети
                        while (startIndex <= field.length - 1 && field[startIndex] == 1) {
                            startIndex += lengthFly;
                        }
                        if (startIndex <= field.length - 1) {
                            field[startIndex] = 1;
                        }

                    } else if (direction.equals("left")) {
                        startIndex -= lengthFly;

                        while (startIndex >= 0 && field[startIndex] == 1) {
                            startIndex -= lengthFly;
                        }
                        if (startIndex >= 0) {
                            field[startIndex] = 1;
                        }
                    }
                }  command = scanner.nextLine();

            }
            for (int number : field) {
                System.out.print(number + " ");

            }
        }
    }

