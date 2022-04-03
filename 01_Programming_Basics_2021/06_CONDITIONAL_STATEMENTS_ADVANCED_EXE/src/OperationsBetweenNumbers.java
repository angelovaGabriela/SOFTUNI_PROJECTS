import java.util.Scanner;

public class OperationsBetweenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();

        int result = 0;
        String oddOrEven = "";

        switch (operator){
            case "+":
                result = number1 + number2;
                if (result % 2 == 0){
                    oddOrEven = "even";
                } else{
                    oddOrEven = "odd";
                }
                System.out.printf("%d %s %d = %d - %s", number1, operator, number2, result, oddOrEven);
                break;
            case "-":
                result = number1 - number2;
                if (result % 2 == 0){
                    oddOrEven = "even";
                } else {
                    oddOrEven = "odd";
                }
                System.out.printf("%d %s %d = %d - %s", number1, operator, number2,result, oddOrEven);
                break;
            case "*":
                result = number1 * number2;
                if (result % 2 == 0){
                    oddOrEven = "even";
                } else {
                    oddOrEven = "odd";
                }
                System.out.printf("%d %s %d = %d - %s", number1, operator, number2, result, oddOrEven);
                break;
            case "/":
                if (number2 == 0){
                    System.out.printf("Cannot divide %d by zero", number1);
                } else {
                    System.out.printf("", System.out);
                    double divideResult = (1.0 * number1) / number2;
                    System.out.printf("%d %s %d = %.2f", number1, operator, number2,divideResult);
                } break;
            case  "%":
                if (number2 == 0){
                    System.out.printf("Cannot divide %d by zero", number1);
                } else {
                    result = number1 % number2;
                    System.out.printf("%d %s %d = %d", number1, operator, number2,result);
                } break;
        }

    }
}
