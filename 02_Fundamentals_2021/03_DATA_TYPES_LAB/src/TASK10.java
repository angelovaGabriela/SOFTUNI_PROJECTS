import java.util.Scanner;

public class TASK10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());


        for (int num = 1; num <= n; num++) {
            int sum = 0;
            int digits = num;

            while (digits > 0) {
                int currentDigit = digits % 10;
                sum = sum + currentDigit;
                digits = digits / 10;

            }
            if (sum == 5 || sum == 7 || sum == 11) {
                System.out.println(num + " -> True");
            } else {
                System.out.println(num + " -> False");
            }
        }
    }
}
