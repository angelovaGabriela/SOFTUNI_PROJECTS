package java_advanced_training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // one = 15 flowers
        // main goal = create 5
        // lilies - stackLilies
        // roses - queue

        ArrayDeque<Integer> stackLilies = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stackLilies:: push);

        ArrayDeque<Integer> queueRoses = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));

// queue.poll() // queue.offer()
// stack.pop() // stack.push()

        int wreaths = 0;
        int sumStoredFlowers = 0;

        while (!stackLilies.isEmpty() && !queueRoses.isEmpty()){

            int sum = stackLilies.peek() + queueRoses.peek();

            if (sum == 15){
                wreaths++;
                stackLilies.pop();
                queueRoses.poll();
            } else if (sum > 15){
               int decrease =  stackLilies.pop();
                stackLilies.push(decrease - 2);
            } else if (sum < 15){
                int storedLilies = stackLilies.pop();
                int storedRoses = queueRoses.poll();

                sumStoredFlowers += storedLilies;
                sumStoredFlowers += storedRoses;
            }
        }

        while (sumStoredFlowers >= 15){
            sumStoredFlowers -= 15;
            wreaths++;
        }

        if (wreaths >= 5){
            System.out.println("You made it, you are going to the competition with " + wreaths + " wreaths!");
        } else {
            int wreathsLeft = 5 - wreaths;
            System.out.println("You didn't make it, you need " + wreathsLeft + " wreaths more!");
        }
    }
}
