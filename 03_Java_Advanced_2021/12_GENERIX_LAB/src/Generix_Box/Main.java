package Generix_Box;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Box<Double> box = new Box(); // String - за да ни предпази от грешки
        for (int i = 0; i < n; i++) {
            Double value = Double.parseDouble( scanner.nextLine());
            box.add(value);
        }
      Double elementToCompare = Double.parseDouble(scanner.nextLine());
        int greaterElementsCount = box.countOfGreaterItems(elementToCompare);
        System.out.println(greaterElementsCount);


    }
}
