package java_advanced_training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stackTulips = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stackTulips:: push);

        ArrayDeque<Integer> queueDaffodils = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));



        int bouquets = 0;
        int storedSum = 0;


        while (!stackTulips.isEmpty() && !queueDaffodils.isEmpty()){
            int sumOfFlowers = stackTulips.peek() + queueDaffodils.peek();

            if (sumOfFlowers == 15){
                bouquets++;
                stackTulips.pop();
                queueDaffodils.poll();
            } else if (sumOfFlowers > 15){

                while (!(sumOfFlowers == 15) && !(sumOfFlowers < 15)) {

                    int decreased = stackTulips.peek() - 2;
                    stackTulips.pop();
                    stackTulips.push(decreased);
                    sumOfFlowers = stackTulips.peek() + queueDaffodils.peek();
                }
                if (sumOfFlowers == 15){
                bouquets++;
                  stackTulips.pop();
                  queueDaffodils.poll();
                } else if (sumOfFlowers < 15){
                   storedSum += sumOfFlowers;
                    stackTulips.pop();
                    queueDaffodils.poll();
                }
            } else {
                storedSum += sumOfFlowers;
                stackTulips.pop();
                queueDaffodils.poll();

            }
        }

        while (storedSum >= 0){

           if (storedSum > 15) {
               storedSum -= 15;
               bouquets++;
           } else {
               break;
           }
        }
        if (bouquets == 5){
            System.out.println("You made it! You go to the competition with " + bouquets + " bouquets!");
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - bouquets);
        }
    }
}
