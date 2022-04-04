import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //calculate the total price of a purchase from a vending machine
        //Until "Start" different coins in the machine.
        //0.1, 0.2, 0.5, 1, and 2 coins
        //"Nuts", "Water", "Crisps", "Soda", "Coke".
        //2.0, 0.7, 1.5, 0.8, 1.0
        // not existing product print “Invalid product”.

        String start = scanner.nextLine();
        double sum = 0;
        boolean validProduct = true;


        while (!start.equals("Start")){
           double coin = Double.parseDouble(start);
           if (coin == 0.1 || coin == 0.2 || coin == 0.5 || coin == 1 || coin == 2) {
               sum += coin;
           } else {
               System.out.printf("Cannot accept %.2f", coin);
           }
           start = scanner.nextLine();

        } String product = scanner.nextLine();
        double productPrice = 0;

        while (!product.equals("End")){
            switch (product){
                case "Nuts":
                    productPrice = 2.0;
                    break;
                case "Water":
                    productPrice = 0.7;
                    break;
                case "Crisps":
                    productPrice = 1.5;
                break;
                case "Soda":
                    productPrice = 0.8;
                    break;
                case "Coke":
                    productPrice = 1.0;
                    break;
                default:
                    System.out.println("Invalid product");
                    product = scanner.nextLine();
                    validProduct = false;
                    break;
            } if (!validProduct) {
                continue;
            } else if (sum < productPrice) {
                System.out.println("Sorry, not enough money");
            } else {
                System.out.printf("Purchased %s%n", product);
                sum -= productPrice;
            }
            product = scanner.nextLine();
        }
        System.out.printf("Change: %.2f", sum);

        }
    }
