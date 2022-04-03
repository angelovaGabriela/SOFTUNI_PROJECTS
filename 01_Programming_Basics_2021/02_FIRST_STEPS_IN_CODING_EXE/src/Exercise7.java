import java.util.Scanner;

public class Exercise7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double priceStrawberry = Double.parseDouble(scanner.nextLine());
        double quantityBananas = Double.parseDouble(scanner.nextLine());
        double quantityOranges = Double.parseDouble(scanner.nextLine());
        double quantityRaspberries = Double.parseDouble(scanner.nextLine());
        double quantityStrawberry = Double.parseDouble(scanner.nextLine());

        double priceRaspberries = priceStrawberry - (priceStrawberry * (50*0.01));
        double priceOranges = priceRaspberries - (priceRaspberries * (40*0.01));
        double priceBananas = priceRaspberries - (priceRaspberries * (80*0.01));

        double sumStrawberry = quantityStrawberry * priceStrawberry;
        double sumBananas = quantityBananas * priceBananas;
        double sumOranges = quantityOranges * priceOranges;
        double sumRaspberries = quantityRaspberries * priceRaspberries;

        double sumGeneral = sumStrawberry + sumBananas + sumOranges + sumRaspberries;

        System.out.printf("%.2f",sumGeneral);


        }

    }

