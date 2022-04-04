package java_advanced_training;

import java.util.*;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> queueLiquids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));


        ArrayDeque <Integer> stackIngredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stackIngredients :: push);

        int sum = 0;
        int breadAmount = 0;
        int cakeAmount = 0;
        int pastryAmount = 0;
        int fruitPie = 0;

        String name = "";
        Map <String, Integer> food = new TreeMap<>();

        while (!queueLiquids.isEmpty() && !stackIngredients.isEmpty()){
            sum = queueLiquids.peek() + stackIngredients.peek();

            if (sum == 25){
                breadAmount++;
                name = "Bread";

                food.put(name,breadAmount);

                queueLiquids.poll();
                stackIngredients.pop();

            } else if (sum == 50){
                cakeAmount++;
                name = "Cake";

                food.put(name, cakeAmount);

                queueLiquids.poll();
                stackIngredients.pop();

            } else  if (sum == 75){
                pastryAmount++;
                name = "Pastry";

                food.put(name, pastryAmount);

                queueLiquids.poll();
                stackIngredients.pop();

            } else if (sum == 100){
                fruitPie++;
                name = "Fruit Pie";

                food.put(name, fruitPie);

                queueLiquids.poll();
                stackIngredients.pop();

            } else {
                queueLiquids.poll();
                int ingredient = stackIngredients.pop();
                stackIngredients.push(ingredient + 3);
                //TODO: if the code doesn't work use .peek and after .push
            }

        }

        if (food.containsKey("Bread") && food.containsKey("Cake") && food.containsKey("Fruit Pie") && food.containsKey("Pastry")){
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        } else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        if (queueLiquids.isEmpty()){
            System.out.println("Liquids left: none");
        } else {
            System.out.print("Liquids left: " + queueLiquids.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }

        if (stackIngredients.isEmpty()){
            System.out.println("Ingredients left: none");
        } else {
            System.out.print("Ingredients left: " + stackIngredients.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }

        food.putIfAbsent("Bread", 0);
        food.putIfAbsent("Cake", 0);
        food.putIfAbsent("Fruit Pie", 0);
        food.putIfAbsent("Pastry", 0);


        food.entrySet().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));



    }
}
