import java.util.Scanner;

public class Problem1_MID_EXAM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysOfTheAdventure = Integer.parseInt(scanner.nextLine());
        int countOfPlayers = Integer.parseInt(scanner.nextLine());

        double groupEnergy = Double.parseDouble(scanner.nextLine());
        double waterForPersonDay = Double.parseDouble(scanner.nextLine());
        double foodForPersonDay = Double.parseDouble(scanner.nextLine());

        double currentEnergy = groupEnergy;


        int dayCount = 0;

        double energyBoost = 0.00;


        double totalWater = daysOfTheAdventure * countOfPlayers * waterForPersonDay;
        double currentWater = totalWater;
        double waterReduce = 0.0;
        double totalFood = daysOfTheAdventure * countOfPlayers * foodForPersonDay;
        double currentFood = totalFood;
        double foodReduce = 0.0;


        for (int i = 1; i <= daysOfTheAdventure; i++) {
            double energyLoss = Double.parseDouble(scanner.nextLine());

            currentEnergy -= energyLoss;

            if (currentEnergy <= 0) {

                break;
            }
            dayCount += 1;
            if (i % 2 == 0) {
                energyBoost = currentEnergy * (5 * 0.01);
                currentEnergy += energyBoost;
                waterReduce = currentWater * (30 * 0.01);
                currentWater = currentWater - waterReduce;
            }
            if (dayCount == 3) {
                dayCount = 0;

                energyBoost = currentEnergy * (10 * 0.01);
                currentEnergy = currentEnergy + energyBoost;

                foodReduce = currentFood / countOfPlayers;
                currentFood = currentFood - foodReduce;


            }


        }
        if (currentEnergy <= 0) {
            System.out.printf("You will run out of energy. You will be left with %.2f food and %.2f water.%n", currentFood, currentWater);
        } else {
            System.out.printf("You are ready for the quest. You will be left with - %.2f energy!%n", currentEnergy);
        }
    }
}
