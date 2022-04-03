package exams;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // males - stack
        ArrayDeque<Integer> stackMales = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(stackMales :: push);

        int successfulMatches = 0;

        // females - queue
        ArrayDeque<Integer> queueFemale = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));

        while (!(stackMales.isEmpty()) && !(queueFemale.isEmpty())){
            int maleValue = stackMales.peek();
            int femaleValue = queueFemale.peek();

            if (maleValue <= 0){
                stackMales.pop();
            } else if (femaleValue <= 0){
                queueFemale.poll();
            } else if (maleValue % 25 == 0){
                stackMales.pop();
                stackMales.pop();
            } else if (femaleValue % 25 == 0){
                queueFemale.poll();
                queueFemale.poll();
            }
            else if (maleValue == femaleValue){
                stackMales.pop();
                queueFemale.poll();
                successfulMatches ++;
            } else {
                queueFemale.poll();
                // decrease the value of the male by 2
                int newValue = stackMales.pop() - 2;
                stackMales.push(newValue);

            }
        }

        System.out.println("Matches: " + successfulMatches);

        if (stackMales.isEmpty()){
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: " + stackMales.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }

        if (queueFemale.isEmpty()){
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: " + queueFemale.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }
    }
}
