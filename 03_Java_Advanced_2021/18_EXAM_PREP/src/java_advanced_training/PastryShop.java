package java_advanced_training;

import java.util.*;
import java.util.stream.Collectors;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> queueLiquids = new ArrayDeque<>();
        Deque<Integer> stackIngredients = new ArrayDeque<>();


        int[] liquidsInput = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.stream(liquidsInput).forEach(queueLiquids::offer);


        int[] ingredientsInput = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(ingredientsInput).forEach(stackIngredients::push);

        int totalSum = 0;
        String food = "";
        List<String> foodCooked = new ArrayList<>();

        while (!queueLiquids.isEmpty() && !stackIngredients.isEmpty()) {

            totalSum = queueLiquids.peek() + stackIngredients.peek();

            if (totalSum == 25) {
                queueLiquids.poll();
                stackIngredients.pop();

                food = "25";
                foodCooked.add(food);

            } else if (totalSum == 50) {
                queueLiquids.poll();
                stackIngredients.pop();

                food = "50";
                foodCooked.add(food);

            } else if (totalSum == 75) {
                queueLiquids.poll();
                stackIngredients.pop();

                food = "75";
                foodCooked.add(food);

            } else if (totalSum == 100) {
                queueLiquids.poll();
                stackIngredients.pop();

                food = "100";
                foodCooked.add(food);

            } else {
                queueLiquids.poll();

                int ingredient = stackIngredients.peek(); // взимам последната подправка, но не я махам
                int newIngredient = ingredient + 3; // увеличавам я с 3
                stackIngredients.pop(); // махам първоначалната стойност
                stackIngredients.push(newIngredient); // добавям обратно в стака на същото място увеличената

            }
        }

        if (foodCooked.contains("25") && foodCooked.contains("50") &&
                foodCooked.contains("75") && foodCooked.contains("100")) {
            System.out.println("Great! You succeeded in cooking all the food!");

        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        if (queueLiquids.isEmpty()) { //
            System.out.println("Liquids left: none");
        } else {
            System.out.print("Liquids left: " + queueLiquids.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();
        }

        if (stackIngredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            System.out.print("Ingredients left: " + stackIngredients.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println();


        }


        int biscuitAmount = 0;
        int cakeAmount = 0;
        int pastryAmount = 0;
        int pieAmount = 0;

        for (String dish : foodCooked) {
            if (dish.equals("25")) {
                biscuitAmount += 1;

            } else if (dish.equals("50")) {
                cakeAmount += 1;

            } else if (dish.equals("75")) {
                pastryAmount += 1;

            } else if (dish.equals("100")) {
                pieAmount += 1;
            }
            //Biscuit	25
            //Cake	    50
            //Pastry	75
            //Pie	   100

        }
        System.out.printf("Biscuit: %d%n" +
                "Cake: %d%n" +
                "Pie: %d%n" +
                "Pastry: %d%n", biscuitAmount, cakeAmount, pieAmount, pastryAmount);


//o	"Biscuit: {amount}"
//o	"Cake: {amount}"
//o	"Pie: {amount}"
//o	"Pastry: {amount}"

    }
}

