import java.util.Scanner;

public class cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String type = scanner.nextLine();
        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        double income = 0.0;
        if (type.equals("Premiere")){
            income = r * c * 12.00;
        } else if (type.equals("Normal")){
            income = r * c * 7.50;

        } else if (type.equals("Discount")){
            income = r * c * 5.00;
        }
        System.out.printf("%.2f leva ",income);
    }
}
