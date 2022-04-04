import java.util.Scanner;

public class workingHours {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double time = Double.parseDouble(scanner.nextLine());
        String dayOfWeek = scanner.nextLine();

        if ((time >= 10 && time <= 18) && ("Monday".equals(dayOfWeek) || "Tuesday".equals(dayOfWeek) || "Wednesday".equals(dayOfWeek) || "Thursday".equals(dayOfWeek) || "Friday".equals(dayOfWeek) || "Saturday".equals(dayOfWeek))) {
            System.out.println("open");

        } else {
            System.out.println("closed");
        }
    }
}

