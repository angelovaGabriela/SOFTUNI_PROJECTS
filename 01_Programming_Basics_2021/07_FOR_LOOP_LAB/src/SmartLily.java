import java.util.Scanner;

public class SmartLily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ageLily = Integer.parseInt(scanner.nextLine());
        double priceLaundry = Double.parseDouble(scanner.nextLine());
        int priceToy = Integer.parseInt(scanner.nextLine());

        int toys = 0;
        double allMoney = 0.0;
        double savedMoney = 0.0;

        for (int i = 1; i <= ageLily; i++){
            if (i % 2 == 0){
                allMoney+= 10;
                savedMoney = savedMoney + allMoney - 1;

            } else {
                toys++;
            }
        } double totalSum = toys * priceToy + savedMoney;
        if (totalSum >= priceLaundry){
            System.out.printf("Yes! %.2f", totalSum - priceLaundry);

        } else {
            System.out.printf("No! %.2f", priceLaundry - totalSum);

        }

    }
}
