import java.math.BigDecimal;
import java.util.Scanner;

public class TASK3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        int n = Integer.parseInt(scanner.nextLine());//5
        BigDecimal sum = new BigDecimal(0);// винаги == 0, 1,
        for (int i = 0; i <n ; i++) {
BigDecimal currentNumber =  new BigDecimal(scanner.nextLine());
sum = sum.add(currentNumber);

        }
        System.out.println(sum);
    }
}
