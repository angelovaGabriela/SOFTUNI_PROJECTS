package java_advanced_training;

import java.util.*;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> queueFirstMagicBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));

        ArrayDeque <Integer> stackSecondMagicBox = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stackSecondMagicBox:: push);


        List<Integer> claimedCollection = new ArrayList<>();

        while (!queueFirstMagicBox.isEmpty() && !stackSecondMagicBox.isEmpty()){

            int firstItem = queueFirstMagicBox.peek();
            int secondItem = stackSecondMagicBox.pop(); // маха го, но го връща като число
            int sumItems = firstItem + secondItem;

            if (sumItems % 2 == 0){
                claimedCollection.add(sumItems);
                queueFirstMagicBox.poll();
            } else {
                queueFirstMagicBox.offer(secondItem); // затова мога да го върна тук и да го добавя в опашката
            }
        }

        String emptyBox = queueFirstMagicBox.isEmpty() ? "First magic box is empty." : "Second magic box is empty.";

        int sumClaimedItems = 0;

        for (int item : claimedCollection) {
            sumClaimedItems += item;
        }

        String itemsValueOutput = sumClaimedItems >= 90
                ? "Wow, your prey was epic! Value: "
                : "Poor prey... Value: " ;

        System.out.println(emptyBox);
        System.out.println(itemsValueOutput + sumClaimedItems);
    }

}
