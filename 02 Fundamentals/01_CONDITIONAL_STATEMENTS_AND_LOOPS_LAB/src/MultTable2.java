import java.util.Scanner;

public class MultTable2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int multiplier = Integer.parseInt(scanner.nextLine());

        do{
            int result = number * multiplier;
            System.out.printf("%d X %d = %d%n", number, multiplier, result);
            multiplier++;
        } while (multiplier <=10 );
    }
}
