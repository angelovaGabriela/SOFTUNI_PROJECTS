package java_advanced_training;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OsPlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayDeque<Integer> stackTasks = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stackTasks:: push);

        ArrayDeque<Integer> queueThreads = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));

        int tasksToKill = Integer.parseInt(scanner.nextLine());


        while (stackTasks.peek() != tasksToKill){
           int currentThreads = queueThreads.peek();
           int currentTask =stackTasks.peek();

            if (currentThreads >= currentTask){
                queueThreads.poll();
                stackTasks.pop();
            } else if (currentThreads < currentTask){
                queueThreads.poll();
            }

        }
        System.out.printf("Thread with value %d killed task %d%n", queueThreads.peek(), tasksToKill);;

        System.out.print(queueThreads.stream().map(String::valueOf)
                .collect(Collectors.joining(" ")));


    }
}
