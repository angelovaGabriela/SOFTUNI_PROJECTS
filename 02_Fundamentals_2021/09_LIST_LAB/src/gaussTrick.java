import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class gaussTrick {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        // 10 11 13 14 15
        // 0  1  2  3  4
        int halfSize = numbers.size() / 2;

        for (int i = 0; i < halfSize; i++) {
        int sum = numbers.get(i)+numbers.get(numbers.size()-1);
        numbers.set (i, sum);
        numbers.remove(numbers.size () - 1);
        }
        for (Integer number : numbers) {
            System.out.print(number + " ");

        }
    }

}
