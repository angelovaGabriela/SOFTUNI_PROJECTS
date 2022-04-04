import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
//Чета ред от интеджери от конзолата сплитнати по запетая и ги броя
        Function<String, Integer> countIntegerInString = str -> str.split(", ").length;
//Събирам числата, които съм прочела от конзолата
        Function<String, Integer> sumIntegersInString  = str ->
                Arrays.stream(str.split(", "))
                .mapToInt(Integer::parseInt) // превръщам ги в инт
                .sum(); // вече са в интеджери и могат да се съберат


// принтирам броя на числата и след това техния сбор
        System.out.println("Count = " + countIntegerInString.apply(input));
        System.out.println("Sum = " + sumIntegersInString.apply(input));
        }
    }

