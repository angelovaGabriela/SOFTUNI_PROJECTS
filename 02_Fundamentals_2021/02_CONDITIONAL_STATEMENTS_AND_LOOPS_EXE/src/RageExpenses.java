import java.util.Scanner;

public class RageExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGames = Integer.parseInt(scanner.nextLine());
        double priceHeadSet = Double.parseDouble(scanner.nextLine());
        double priceMouse = Double.parseDouble(scanner.nextLine());
        double priceKeyboard = Double.parseDouble(scanner.nextLine());
        double priceDisplay = Double.parseDouble(scanner.nextLine());


        int countHeadSet = lostGames / 2;
        int countMouse = lostGames / 3;
        int countKeyboard = lostGames / 6;
        int countDisplay = lostGames / 12;

        double expenses = (countHeadSet * priceHeadSet)+(priceMouse * countMouse)+(countKeyboard * priceKeyboard)+(priceDisplay * countDisplay);

        System.out.printf("Rage expenses: %.2f lv.", expenses);
    }
}
