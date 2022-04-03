import java.util.Scanner;

public class Traveling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String destination = scanner.nextLine(); //държава или "End"
        //стоп -> destination == "End"
        //продължаваме -> destination != "End"
        while(!destination.equals("End")) {
            //държава
            double excursionPrice = Double.parseDouble(scanner.nextLine());
            //събирам пари
            double availableMoney = 0;
            //стоп -> availableMoney >= excursionPrice
            //продължава -> availableMoney < excursionPrice
            while (availableMoney < excursionPrice) {
                double savedMoney = Double.parseDouble(scanner.nextLine());
                availableMoney += savedMoney;
            }
            //налични пари > пари за екскурзията
            System.out.printf("Going to %s!%n", destination);
            destination = scanner.nextLine();
        }
    }
}