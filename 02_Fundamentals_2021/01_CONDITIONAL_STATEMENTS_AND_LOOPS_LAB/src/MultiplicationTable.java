import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
int multiplier = 1;
        while (multiplier <=10 ){
            int result = number * multiplier;
            System.out.printf("%d X %d = %d%n", number, multiplier, result);
            multiplier++;
        }
    }
}
