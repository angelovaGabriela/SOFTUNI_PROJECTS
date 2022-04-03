import java.util.Scanner;

public class TASK1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int metres = Integer.parseInt(scanner.nextLine());
        double kilometres = 1.0 * metres/1000;

        System.out.printf("%.2f", kilometres);
    }
}
