import java.util.Scanner;

public class TASK2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double dollars = Double.parseDouble(scanner.nextLine());
        double pounds = dollars * 1.31;
        System.out.printf("%.3f", pounds);
    }
}
