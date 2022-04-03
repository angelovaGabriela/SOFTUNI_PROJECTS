import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Integer> numbers = Arrays.stream(input.split("\\s+")).mapToInt(Integer :: parseInt).boxed().collect(Collectors.toList());
        int n = Integer.parseInt(scanner.nextLine());                                                //превръща в стрийм от интеджери

        Collections.reverse(numbers); //обръщаме го

        Predicate<Integer>  checkDivision = number -> number % n == 0;
        //премахни го ако предицата е равен на true
        numbers.removeIf(checkDivision);

        numbers.forEach(number -> System.out.print(number + " "));

    }

}
