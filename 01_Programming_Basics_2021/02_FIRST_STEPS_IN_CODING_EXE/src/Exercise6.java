import java.util.Scanner;

public class Exercise6 {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int days = Integer.parseInt(scanner.nextLine());
            int chefs = Integer.parseInt(scanner.nextLine());
            int cakes = Integer.parseInt(scanner.nextLine());
            int waffles = Integer.parseInt(scanner.nextLine());
            int pancakes = Integer.parseInt(scanner.nextLine());


            double priceCakes = cakes * 45;
            double priceWaffles = waffles * 5.80;
            double pricePancakes = pancakes * 3.20;

            double sumOneDayAllChefs = (priceCakes + priceWaffles + pricePancakes) * chefs;
            double sumAllDays = sumOneDayAllChefs * days;
            double sumAfterCharges = sumAllDays - (sumAllDays/8);

        System.out.printf("%.2f", sumAfterCharges);


        //обща сума за един ден от всички сладкари = (цена торти + цена гофрети + цена палачинки) * сладкари
        // сумата събрана от цялата кампания = обща сума за 1 ден от всички сладкари * общ брой дни
        //сума след покриване на разходите = сума от цялата кампания - (0.8 * сума от цялата кампания)
        // парите, които са събрани
        //форматирани до втората цифра след запетаята

        }
    }

