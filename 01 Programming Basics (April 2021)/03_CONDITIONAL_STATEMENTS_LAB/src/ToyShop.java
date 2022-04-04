import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double priceVacation = Double.parseDouble(scanner.nextLine());
        int numberPuzzle = Integer.parseInt(scanner.nextLine());
        int numberDolls = Integer.parseInt(scanner.nextLine());
        int numberTeddyBears = Integer.parseInt(scanner.nextLine());
        int numberMignon = Integer.parseInt(scanner.nextLine());
        int numberTrucks = Integer.parseInt(scanner.nextLine());

        int numberToys = numberPuzzle + numberDolls + numberTeddyBears + numberMignon + numberTrucks;

        double pricePuzzle = numberPuzzle * 2.60;
        double priceDolls = numberDolls * 3;
        double priceTeddyBears = numberTeddyBears * 4.10;
        double priceMignon = numberMignon * 8.20;
        double priceTrucks = numberTrucks * 2;


        double fullPrice = pricePuzzle + priceDolls + priceTeddyBears + priceMignon + priceTrucks;


        if (numberToys >= 50) {
            fullPrice = fullPrice - 0.25 * fullPrice;
        }

        double loan = fullPrice * 0.1;
        double finalPrice = fullPrice - loan;
        double priceEnough = finalPrice - priceVacation;
        double notEnough = priceVacation - finalPrice;

        if (finalPrice >= priceVacation){
            System.out.printf("Yes! %.2f lv left.", priceEnough);

        } else {
            System.out.printf("Not enough money! %.2f lv needed.", notEnough);
        }




        }

    }
