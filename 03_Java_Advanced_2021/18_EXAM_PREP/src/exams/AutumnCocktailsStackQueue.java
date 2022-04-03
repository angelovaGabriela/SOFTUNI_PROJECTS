package exams;


import java.util.*;
import java.util.stream.Collectors;

public class AutumnCocktailsStackQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       //first bucket of ingredients - queue
        ArrayDeque<Integer> queueIngredients = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque :: new));
       // the last freshness level value - stackFreshnessLevel

        ArrayDeque <Integer> stackFreshnessLevel = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(stackFreshnessLevel :: push);

        Map <String, Integer> cocktails = new TreeMap<>();
        String name = "";
     int pearSourAmount = 0; // 150
     int theHarvestAmount = 0; // 250
     int appleHinnyAmount = 0; // 300
     int highFashionAmount = 0; // 400

        int sum = 0;
        while(!queueIngredients.isEmpty() && !stackFreshnessLevel.isEmpty()){
            //In case you have an ingredient with a value of 0 you have to remove it and continue mixing the cocktails.
            if (queueIngredients.peek() != 0){
                sum = queueIngredients.peek() * stackFreshnessLevel.peek();
            } else {
                queueIngredients.poll();
                continue; // goes up from the top of the loop, continue mixing the cocktails
            }




            if(sum == 150){
                pearSourAmount++;
                name = "Pear Sour";
                cocktails.put(name, pearSourAmount);

                queueIngredients.poll();
                stackFreshnessLevel.pop();

            } else if (sum == 250){
            theHarvestAmount++;
            name = "The Harvest";
            cocktails.put(name, theHarvestAmount);

            queueIngredients.poll();
            stackFreshnessLevel.pop();
            } else if (sum == 300){
                appleHinnyAmount++;
                name = "Apple Hinny";
                cocktails.put(name,appleHinnyAmount);

                queueIngredients.poll();
                stackFreshnessLevel.pop();
            } else if (sum == 400){
                highFashionAmount++;
                name = "High Fashion";
                cocktails.put(name, highFashionAmount);

                queueIngredients.poll();
                stackFreshnessLevel.pop();
            } else {
                // remove the freshness level,
                // increase the ingredient value by 5, - queue
                // remove it from the first position and add it at the end.
                stackFreshnessLevel.pop();
               int ingredientToIncrease = queueIngredients.getFirst();
               int newIngredient = ingredientToIncrease + 5;
               queueIngredients.poll();
               queueIngredients.offer(newIngredient);

            }


        }
        //Pear Sour
        //The Harvest
        //Apple Hinny
        //High Fashion

        if (cocktails.containsKey("Pear Sour") && cocktails.containsKey("The Harvest") && cocktails.containsKey("Apple Hinny") && cocktails.containsKey("The Harvest") &&  cocktails.containsKey("High Fashion")){
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }
        int ingredientSum = 0;

        while (!queueIngredients.isEmpty()){
         int current = queueIngredients.poll();
            ingredientSum += current;
        } if (ingredientSum > 0){
            System.out.println("Ingredients left: " + ingredientSum);
        }

        cocktails.forEach((key, value) -> System.out.printf(" # %s --> %d%n", key, value));

            }


        }



