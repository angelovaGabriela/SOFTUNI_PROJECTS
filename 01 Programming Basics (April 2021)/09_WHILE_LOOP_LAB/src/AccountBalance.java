import java.util.Scanner;

public class AccountBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        double totalSum = 0.00;
         String text = scanner.nextLine();

         while (!text.equals("NoMoreMoney")){
             double sum = Double.parseDouble(text);
             if (sum < 0 ){
                 System.out.println("Invalid operation!");
                 break;
             } else {
                 System.out.printf("Increase: %.2f%n ", sum);
                 totalSum += sum;
             }
             text = scanner.nextLine();
         }
        System.out.printf("Total: %.2f", totalSum);
    }
}
