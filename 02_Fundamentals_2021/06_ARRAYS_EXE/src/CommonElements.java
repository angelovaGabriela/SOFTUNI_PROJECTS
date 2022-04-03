import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstRow = scanner.nextLine();
        String secondRow = scanner.nextLine();

        String [] array1 = firstRow.split(" ");
        String [] array2 = secondRow.split(" ");

        for (String secondWord: array2) {
            for (String firstWord : array1) {
                if (secondWord.equals(firstWord)){
                    System.out.print(firstWord + " ");
                }

            }

        }
    }
}
