import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double vacationCost = Double.parseDouble(scanner.nextLine()); // колко струва ваканцията
        double availableCash = Double.parseDouble(scanner.nextLine()); // парите, с които ние разполагаме

        int days = 0; // брояч за всички дни
        int daysSpend = 0; // брояч за последователните дни, в които тя харчи

        // ДОКАТО парите, с които разполагаме са по-малко от парите за ваканцията
        // И дните, в които тя харчи са по-малко от 5, то ....
        while (availableCash < vacationCost && daysSpend < 5) {
            String action = scanner.nextLine(); // действието - spend / save
            double cash = Double.parseDouble(scanner.nextLine()); // парите, според които ще спестявам или ще харча
            days++; // на всяко завъртане на цикъла - дните се увеличават с един

            if (action.equals("spend")) {
                availableCash -= cash; // availableCash = availableCash - cash;
                daysSpend++;
                if (availableCash < 0) {
                    availableCash = 0;
                }
            } else if (action.equals("save")) {
                availableCash += cash; // availableCash = availableCash + cash;
                daysSpend = 0;
            }
        }
        if (availableCash >= vacationCost) {
            System.out.printf("You saved the money for %d days.", days);
        }
        if (daysSpend == 5) {
            System.out.println("You can't save the money.");
            System.out.printf("%d", days);
            //System.out.printf("You can't save the money.%n%d", days);
        }
    }
}


