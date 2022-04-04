import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int numberNights = Integer.parseInt(scanner.nextLine());

        double priceApart = 0.00;
        double priceStudio = 0.00;

        switch (month){
            case "May":
            case "October":
                priceStudio = numberNights * 50;
                priceApart = numberNights * 65;
                if (numberNights > 14){
                    priceStudio = priceStudio - (priceStudio * 0.3);
                    priceApart = priceApart - (priceApart * 0.1);
                } else if (numberNights > 7){
                    priceStudio = priceStudio - (priceStudio * 0.05);
                }
                break;

            case "June":
            case"September":
                priceStudio = numberNights * 75.20;
                priceApart = numberNights * 68.70;
                if (numberNights > 14){
                    priceStudio = priceStudio - (priceStudio * 0.2);
                    priceApart = priceApart - (priceApart * 0.1);
                }
                break;

            case "July":
            case "August":
                priceStudio = numberNights * 76;
                priceApart = numberNights * 77;
                if (numberNights > 14){
                    priceApart = priceApart - (priceApart * 0.1);
                    priceStudio = numberNights * 76;
                }
                break;
        }
        System.out.printf("Apartment: %.2f lv. %n", priceApart);
        System.out.printf("Studio: %.2f lv.", priceStudio);

    }
}
