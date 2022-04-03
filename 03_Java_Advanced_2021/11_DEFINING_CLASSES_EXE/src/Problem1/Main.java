package Problem1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(scanner.nextLine());

        List<OpinionPoll> persons = new ArrayList<>();


        for (int i = 0; i < numberOfPeople; i++) {
            String[] input = scanner.nextLine().split(" ");
            String name = input[0];
            int age = Integer.parseInt(input[1]);
            OpinionPoll person  = new OpinionPoll(name, age);
            persons.add(person);

        }

        persons
                .stream()
                .filter(person -> person.getAge() > 30 )
                .sorted(Comparator.comparing(OpinionPoll::getName))
                .forEach(System.out::println);
    }
}
