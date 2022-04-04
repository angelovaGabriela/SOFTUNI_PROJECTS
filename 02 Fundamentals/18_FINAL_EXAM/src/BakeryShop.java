import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BakeryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String commands = scanner.nextLine();

        Map<String,Integer> bakery = new TreeMap<>();
        int foodSold = 0;

        while (!commands.equals("Complete")){

            String[] commandParts = commands.split(" ");
            String toDo = commandParts[0];

            int quantity = Integer.parseInt(commandParts[1]);
            String food = commandParts[2];

            switch (toDo){
                case "Receive":
                        if(quantity <= 0){
                         continue;
                        }
                    if (!bakery.containsKey(food)) {
                        bakery.put(food,quantity);
                    } else {
                        int newQuantity = bakery.get(food) + quantity;
                        bakery.put(food, newQuantity);
                    }

                    break;
                case "Sell":
                    if (!bakery.containsKey(food)){
                        System.out.println("You do not have any " + food + ".");
                    } else if (bakery.get(food) < quantity){
                      int soldQuantity = bakery.get(food);
                      System.out.printf("There aren't enough %s. You sold the last %d of them.%n", food, soldQuantity);
                      foodSold += bakery.get(food);
                      bakery.remove(food);


                    } else {
                        int decreased = bakery.get(food) - quantity;
                        bakery.put(food, decreased);
                        System.out.printf("You sold %d %s.%n", quantity, food);
                        foodSold+= quantity;
                        if (decreased <= 0){
                            bakery.remove(food);
                        }

                    }
                    break;
            }



            commands = scanner.nextLine();
        }

        bakery.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
        System.out.printf("All sold: %d goods", foodSold);

    }
}
