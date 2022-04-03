import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer :: parseInt).collect(Collectors.toList());
        //отпечатваме индекса на най-малкото число

        Consumer<List<Integer>> printMinIndexOf = list -> {
            int min = Collections.min(list); // взимам минималния елемент от листа
            System.out.println(list.lastIndexOf(min)); //най-десния, последният индекс на минималния елемент
        };

    printMinIndexOf.accept(numbers);
    }
}
