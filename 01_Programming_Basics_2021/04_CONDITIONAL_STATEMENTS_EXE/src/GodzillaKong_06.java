import java.util.Scanner;

public class GodzillaKong_06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double budgetMovie = Double.parseDouble(scanner.nextLine());
        int people = Integer.parseInt(scanner.nextLine());
        double priceClothesPerson = Double.parseDouble(scanner.nextLine());

        double decoration = budgetMovie * 0.1;
        double fullPriceClothes = people * priceClothesPerson;


        if (people >= 150){
            fullPriceClothes = fullPriceClothes - (fullPriceClothes * 0.1);
        }

        double fullPriceMovie = decoration + fullPriceClothes;
        if (fullPriceMovie > budgetMovie) {
            System.out.println("Not enough money!");
            System.out.printf("Wingard needs %.2f leva more.", fullPriceMovie - budgetMovie);
        } else  {
            System.out.println("Action!");
            System.out.printf("Wingard starts filming with %.2f leva left.", budgetMovie - fullPriceMovie);
        }

            }




    }


