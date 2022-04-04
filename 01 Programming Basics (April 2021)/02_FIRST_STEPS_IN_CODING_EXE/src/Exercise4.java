import java.util.Scanner;

public class Exercise4 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int numberPages = Integer.parseInt(scanner.nextLine());
            int numberPagesOneHour = Integer.parseInt(scanner.nextLine());
            int numberDaysBook = Integer.parseInt(scanner.nextLine());

            int timeNeeded = numberPages / numberPagesOneHour;
            int timePerDay = timeNeeded / numberDaysBook;

            System.out.printf("%d", timePerDay);
}
}
