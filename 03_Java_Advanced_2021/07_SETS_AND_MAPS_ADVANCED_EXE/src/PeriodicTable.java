import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        TreeSet<String> elements = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] inputElements = input.split("\\s+");

          //  for (String element : inputElements) {
          //     elements.add(element);
          // }

          //вградена функционалност
          Collections.addAll(elements, inputElements);
        } // всички инпут елементи ги добавям а сета (първото в скобата)


        elements.forEach(element -> System.out.print(element + " "));


        }
    }
