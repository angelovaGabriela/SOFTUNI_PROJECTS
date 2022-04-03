import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine()); // n-на брой цели числа, въведени от потребителя
        int sum = 0;
        int max = Integer.MIN_VALUE; // -10000000

        for(int i = 1; i <= n; i++) { // от 1 до 7
            int number = Integer.parseInt(scanner.nextLine()); // ми създава число при всяка итерация на цикъла
            sum = sum + number;

            if (number > max) {
                max = number;
            }
        }
        sum = sum - max;

        if (max == sum) {
            System.out.println("Yes");
            System.out.printf("Sum = %d", max);
        } else {
            System.out.println("No");
            System.out.printf("Diff = %d", Math.abs(sum - max));
        }
    }
}