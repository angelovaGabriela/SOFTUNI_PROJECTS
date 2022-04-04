import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsForHonor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] names = input.split("\\s+");

        Consumer<String> printName = name -> System.out.println("Sir " + name);
//обхожда масива и за всеки елемент го принтира
        Arrays.stream(names).forEach(printName);

    }
}
