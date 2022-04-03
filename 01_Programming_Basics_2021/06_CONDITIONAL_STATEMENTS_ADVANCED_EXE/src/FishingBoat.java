import java.util.Scanner;

public class FishingBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budgetGroup = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int fishersNumber = Integer.parseInt(scanner.nextLine());
        double boatPrice = 0.00;


        switch (season){
            case "Spring":
                boatPrice = 3000;
                break;
            case "Summer":
            case "Autumn":
                boatPrice = 4200;

                break;
            case "Winter":
                boatPrice = 2600;
                break;
            default:
                break;
        }
        if (fishersNumber <= 6 ){
            boatPrice = boatPrice - (boatPrice * 0.1);
        } else if ((fishersNumber > 7 ) && (fishersNumber <= 11)){
            boatPrice = boatPrice - (boatPrice * 0.15);
        } else if (fishersNumber > 12){
            boatPrice = boatPrice - (boatPrice * 0.25);
        } else if (fishersNumber % 2 == 0){
            boatPrice = boatPrice - (boatPrice * 0.05);
        }
        if ((fishersNumber % 2 == 0) && !(season.equals("Autumn"))){
            boatPrice = boatPrice -(boatPrice * 0.05);
        }
        if (budgetGroup >= boatPrice) {
            System.out.printf("Yes! You have %.2f leva left.%n", budgetGroup - boatPrice);
        } else  {
            System.out.printf("Not enough money! You need %.2f leva.", boatPrice - budgetGroup);
        }
    }
}
