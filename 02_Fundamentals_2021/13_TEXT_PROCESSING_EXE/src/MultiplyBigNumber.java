import java.math.BigInteger;
import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNumberStr = scanner.nextLine();
        String secondNumberStr = scanner.nextLine();

        BigInteger firstNumber = new BigInteger(firstNumberStr);
        BigInteger secondNumber = new BigInteger(secondNumberStr);


        System.out.println(firstNumber.multiply(secondNumber));
    }
}
