import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double numberOfGroup = Integer.parseInt(scanner.nextLine());
        String typeOfGroup = scanner.nextLine();
        String dayOfWeek = scanner.nextLine();
        double price = 0;
        double totalPrice = 0;

        switch (dayOfWeek){
            case "Friday":
                if (typeOfGroup.equals("Students")){
                    price = 8.45;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Business")){
                    price = 10.90;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Regular")) {
                    price = 15;
                    totalPrice = numberOfGroup * price;
                }
            break;
            case "Saturday":
                if (typeOfGroup.equals("Students")){
                    price = 9.80;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Business")){
                    price = 15.60;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Regular")) {
                    price = 20;
                    totalPrice = numberOfGroup * price;
                }
                break;
            case "Sunday":
                if (typeOfGroup.equals("Students")){
                    price = 10.46;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Business")){
                    price = 16;
                    totalPrice = numberOfGroup * price;
                } else if (typeOfGroup.equals("Regular")) {
                    price = 22.50;
                    totalPrice = numberOfGroup * price;
                }
                break;
        } if (numberOfGroup >= 30 && typeOfGroup.equals("Students")){
            totalPrice = totalPrice - (totalPrice * 0.15);

        } else if (numberOfGroup >= 100 && typeOfGroup.equals("Business")){
            totalPrice = totalPrice - (10 * price);
        } else if (numberOfGroup >= 10 && numberOfGroup <= 20 && typeOfGroup.equals("Regular")){
            totalPrice = totalPrice - (totalPrice * 0.05);
        }
        System.out.printf("Total price: %.2f", totalPrice);

    }
}

