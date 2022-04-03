import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());

       double factorialN1 = factorialNumber1(number1);
       double factorialN2 = factorialNumber2(number2);
       double division =  factorialN1 / factorialN2;

        System.out.printf("%.2f", division);

    }



    private static double factorialNumber1(int number1) {


            double factorial1 = 1;
            for (int i = number1; i >= 1; i--) {
                factorial1 = factorial1 * i;
            }
            return factorial1;
        }




    private static double factorialNumber2(int number2) {

            double factorial2 = 1;
            for (int i = number2; i >= 1; i--) {
                factorial2 = factorial2 * i;
            }


        return factorial2;

    }
}




