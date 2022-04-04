import java.util.Scanner;

public class FruitShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fruit = scanner.nextLine();
        String day = scanner.nextLine();
        double amount = Double.parseDouble(scanner.nextLine());
        double price = 0;

        switch (fruit) {
            case "banana":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 2.7;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 2.5;
                } else {
                    System.out.println("error");
                } break;
            case "apple":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 1.25;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 1.2;
                } else {
                    System.out.println("error");
                } break;
            case "orange":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 0.9;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 0.85;
                } else {
                    System.out.println("error");
                } break;
            case "grapefruit":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 1.6;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 1.45;
                } else {
                    System.out.println("error");
                } break;
            case "kiwi":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 3;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 2.7;
                } else {
                    System.out.println("error");
                } break;
            case "pineapple":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 5.6;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 5.5;
                } else {
                    System.out.println("error");
                } break;
            case "grapes":
                if (day.equals("Saturday") || day.equals("Sunday")) {
                    price = amount * 4.2;
                } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
                    price = amount * 3.85;
                } else {
                    System.out.println("error");
                } break;
            default:
                System.out.println("error");

        } if (price > 0)
            System.out.printf("%.2f", price);


    }
}