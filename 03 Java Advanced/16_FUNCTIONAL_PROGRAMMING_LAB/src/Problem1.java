import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

// чета ред от интежери от конзолата
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer :: parseInt)
                .collect(Collectors.toList());
// Събирам четните числа в лист
        List<Integer> evenNumbers = numbers
                .stream()
                .filter(e-> e % 2 == 0) // цедка
                .collect(Collectors.toList());
// Принтирам четните числа
      String firstOutPut =  evenNumbers
                .stream()
                .map(String :: valueOf)
                .collect(Collectors.joining(", "));

        System.out.println(firstOutPut);
// сортирам ги във възходящ ред
   String secondOutPut =  evenNumbers
            .stream()
            .sorted()
            .map(String :: valueOf)
            .collect(Collectors.joining(", "));

        System.out.println(secondOutPut);
    }
}
