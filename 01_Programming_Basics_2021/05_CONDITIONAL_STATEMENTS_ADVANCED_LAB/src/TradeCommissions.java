import java.util.Scanner;

public class TradeCommissions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();
        double salesPrice = Double.parseDouble(scanner.nextLine());
        double commission = 0;

        switch (cityName){
            case "Sofia":
                if ((0 <= salesPrice) && (salesPrice <= 500)){
                    System.out.printf("%.2f", commission = salesPrice * 0.05);
                } else if ((500 < salesPrice) && (salesPrice <= 1000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.07);
                } else if ((1000 < salesPrice) && ( salesPrice <= 10000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.08);
                } else if (salesPrice > 10000) {
                    System.out.printf("%.2f", commission = salesPrice * 0.12);
                } else {
                    System.out.println("error");
                }
                break;
            case "Varna":
                if ((0 <= salesPrice) && (salesPrice <= 500)){
                    System.out.printf("%.2f", commission = salesPrice * 0.045);
                } else if ((500 < salesPrice) && (salesPrice <= 1000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.075);
                } else if ((1000 < salesPrice) && ( salesPrice <= 10000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.1);
                } else if (salesPrice > 10000) {
                    System.out.printf("%.2f", commission = salesPrice * 0.13);
                } else {
                    System.out.println("error");
                }
                break;
            case "Plovdiv":
                if ((0 <= salesPrice) && (salesPrice <= 500)){
                    System.out.printf("%.2f", commission = salesPrice * 0.055);
                } else if ((500 < salesPrice) && (salesPrice <= 1000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.08);
                } else if ((1000 < salesPrice) && ( salesPrice <= 10000)){
                    System.out.printf("%.2f", commission = salesPrice * 0.12);
                } else if (salesPrice > 10000) {
                    System.out.printf("%.2f", commission = salesPrice * 0.145);
                } else {
                    System.out.println("error");
                }
                break;

            default: System.out.println("error");


        }

    }
}

