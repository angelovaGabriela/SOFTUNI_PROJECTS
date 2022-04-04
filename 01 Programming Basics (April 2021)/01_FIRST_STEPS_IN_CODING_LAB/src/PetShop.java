import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    int numberDogs = Integer.parseInt(scanner.nextLine());
    int numberOtherAnimals = Integer.parseInt(scanner.nextLine());

    double priceDogFood = numberDogs * 2.50;
    double priceOtherAnimals = numberOtherAnimals * 4.00;
    double fullPrice = priceDogFood + priceOtherAnimals;

    System.out.printf("%.1f lv. " , fullPrice);

    }
}
