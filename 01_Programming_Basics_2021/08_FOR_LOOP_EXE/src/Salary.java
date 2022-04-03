import java.util.Scanner;

public class Salary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tabs = Integer.parseInt(scanner.nextLine());
        int salary = Integer.parseInt(scanner.nextLine());

        boolean isLost = false;

        for (int i = 0; i < tabs; i++) {
            String site = scanner.nextLine();
            if (site.equals("Facebook")) {
                salary = salary - 150;

            } else if (site.equals("Instagram")) {
                salary = salary - 100;

            } else if (site.equals("Reddit")) {
                salary = salary - 50;
            }
            if (salary <= 0) {
                isLost = true;
                break;
            }
        } if (isLost){
            System.out.printf("You have lost your salary.");

        } else{
            System.out.printf("%d", salary);
        }
    }
}
