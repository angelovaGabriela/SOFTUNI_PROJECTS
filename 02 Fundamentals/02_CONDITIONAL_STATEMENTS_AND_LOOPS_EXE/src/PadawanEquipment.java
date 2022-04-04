import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double amountOfMoney = Double.parseDouble(scanner.nextLine());
        int studentsNumber = Integer.parseInt(scanner.nextLine());
        double saberPrice = Double.parseDouble(scanner.nextLine());
        double robePrice = Double.parseDouble(scanner.nextLine());
        double beltPrice = Double.parseDouble(scanner.nextLine());

        //total money needed (enough or not enough, how much more he needs)
        // the light sabers sometimes breaks, he must buy 10% more math round up
        // every sixth belt is free
        //sabresPrice*(studentsCount + 10%)
        // + robesPrice * (studentsCount) +
        // beltsPrice*(studentsCount-freeBelts)

        double totalPrice = (Math.ceil(studentsNumber + 0.10 * studentsNumber)) * saberPrice + robePrice * studentsNumber + beltPrice * (studentsNumber - (studentsNumber/6));

        if (totalPrice <= amountOfMoney){
            System.out.printf("The money is enough - it would cost %.2flv.", totalPrice);
        } else {
            System.out.printf("George Lucas will need %.2flv more.", totalPrice - amountOfMoney);
        }

        }
}
