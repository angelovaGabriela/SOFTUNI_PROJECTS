package java_advanced_training;

import java.util.*;
import java.util.stream.Collectors;

public class Bombs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // bomb effects - queue
        ArrayDeque<Integer> queueBombEffects = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));

        // bomb casings - stackBombCasings
        ArrayDeque <Integer> stackBombCasings = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stackBombCasings:: push);

        int sum = 0;
        String name = "";
        int daturaAmount = 0;
        int cherryAmount= 0;
        int smokeAmount = 0;

        Map <String, Integer> bombsCollection = new TreeMap<>();

        while (!queueBombEffects.isEmpty() && !stackBombCasings.isEmpty()){
        sum = queueBombEffects.peek() + stackBombCasings.peek();



        if (sum == 40){
            daturaAmount++;
            name = "Datura Bombs";
            bombsCollection.put(name, daturaAmount);

            queueBombEffects.poll();
            stackBombCasings.pop();

        } else if (sum == 60){
            cherryAmount++;
            name = "Cherry Bombs";
            bombsCollection.put(name, cherryAmount);

            queueBombEffects.poll();
            stackBombCasings.pop();

        } else if (sum == 120){
            smokeAmount++;
            name = "Smoke Decoy Bombs";
            bombsCollection.put(name, smokeAmount);

            queueBombEffects.poll();
            stackBombCasings.pop();

        } else {
            int valueToDecreased = stackBombCasings.pop();
            int decreased = valueToDecreased - 5;
            stackBombCasings.push(decreased);
        }

            if (daturaAmount >= 3 && cherryAmount >= 3 && smokeAmount >= 3) {
                break;
            }

        }

        if (daturaAmount >= 3 && cherryAmount >= 3 && smokeAmount >= 3){
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        if (queueBombEffects.isEmpty()){
            System.out.println("Bomb Effects: empty");
        } else {
            System.out.print("Bomb Effects: " + queueBombEffects.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }
        if (stackBombCasings.isEmpty()){
            System.out.println("Bomb Casings: empty");
        } else {
            System.out.print("Bomb Casings: " + stackBombCasings.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }


        bombsCollection.putIfAbsent("Datura Bombs", 0);
        bombsCollection.putIfAbsent("Cherry Bombs", 0);
        bombsCollection.putIfAbsent("Smoke Decoy Bombs", 0);

        bombsCollection.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));



        //•	Datura Bombs: 40
        //•	Cherry Bombs: 60
        //•	Smoke Decoy Bombs: 120
    }
}
