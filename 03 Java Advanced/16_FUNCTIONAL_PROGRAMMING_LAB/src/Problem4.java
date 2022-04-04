import java.util.Arrays;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// Унарен оператор валю умножено по 20%
        UnaryOperator <Double> vat = val -> val * 1.20;

        System.out.println("Prices with VAT:");
// пускам стрийм по парсвам към дабъл и след това мапвам и с unaryOperator
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Double :: parseDouble)
                .map(vat)
                .forEach(p -> System.out.printf("%.2f%n", p));

    }
}
