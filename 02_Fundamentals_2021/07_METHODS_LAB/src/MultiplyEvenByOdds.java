import java.util.Scanner;

public class MultiplyEvenByOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());
        int multiple = getMultipleOfEvenAndOdds(Math.abs(input));
        System.out.println((Math.abs(multiple)));
    }

    private static int getMultipleOfEvenAndOdds(int number) {
    int evenSum = getMultipleOfEven(number);
    int oddSum = getMultiplyOfOdd(number);
    return evenSum * oddSum;
    }

    private static int getMultiplyOfOdd(int number) {
        int oddSum = 0;
        while(number > 0){
            int digit = number % 10; // взимам си последната цифра
            if (digit % 2 == 1){
                oddSum+=digit;
            } number = number / 10;

        } return oddSum;
    }

    private static int getMultipleOfEven(int number) {
        int evenSum = 0;
        while(number > 0){
            int digit = number % 10;
            if(digit % 2 == 0){
                evenSum+=digit;
            } number = number / 10;

        } return evenSum;

    }
}

