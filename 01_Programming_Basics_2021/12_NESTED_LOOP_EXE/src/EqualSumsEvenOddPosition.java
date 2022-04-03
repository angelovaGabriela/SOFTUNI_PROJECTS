import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());



        for (int i = n; i <= m; i++){
            int oddSum = 0;
            int evenSum = 0;

            String currentNum = "" + i;

            for (int j = 0; j < 6; j++){
            if (j % 2 == 0){
                evenSum += currentNum.charAt(j);

            } else {
                oddSum += currentNum.charAt(j);

            }
            } if (oddSum == evenSum){
                System.out.print(i + " ");
            }
        }
    }
}
