import java.util.Scanner;

public class addAndSubstract {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        int number3 = Integer.parseInt(scanner.nextLine());

        int sum = sumMethod(number1,number2,number3);
        subtractMethod(sum, number3);
    }



    private static int sumMethod(int number1, int number2, int number3) {
        int sum = number1 + number2;
        return sum;
    }

    private static void subtractMethod(int sum, int number3) {
        System.out.println(sum-number3);
    }
}
