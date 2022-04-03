import java.util.Scanner;

public class printingTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = Integer.parseInt(scanner.nextLine());
        printPyramid(height);// метод, който държи височината
    }

    private static void printPyramid(int height) {
        printTopHeight(height);
        printBottomHeight(height);
    }



    private static void printTopHeight(int height) {
        for (int i = 1; i < height; i++) {
            printSingleLine(i);
        }
    }

    private static void printSingleLine(int length) {
        for (int i = 1; i <= length ; i++) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    private static void printBottomHeight(int height) {
        for (int i = height; i >= 1 ; i--) {
            printSingleLine(i);

        }
    }
}
