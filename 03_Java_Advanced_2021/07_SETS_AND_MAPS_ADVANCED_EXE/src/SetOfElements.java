import java.util.LinkedHashSet;
import java.util.Scanner;

public class SetOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    //да извадя елементите, които ги има и в двата сета
    //ВГРАДЕНА ФУНКЦИЯ

    LinkedHashSet<Integer> firstSet = new LinkedHashSet<>();
    LinkedHashSet<Integer> secondSet = new LinkedHashSet<>();

        String input = scanner.nextLine();
    int countFirstSet = Integer.parseInt(input.split("\\s+")[0]);
    int countSecondSet = Integer.parseInt(input.split("\\s+")[1]);
  //пълня сетовете
        for (int i = 0; i < countFirstSet; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            firstSet.add(number);
        }
        for (int i = 0; i < countSecondSet; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            secondSet.add(number);
        }

        firstSet.retainAll(secondSet);
        // от първия сет премахва елементите, които ги няма във втория
        // в първия сет сет запазват само елементите, които ги има в двата сета

        firstSet.forEach(number -> System.out.print(number + " "));
    }
}
