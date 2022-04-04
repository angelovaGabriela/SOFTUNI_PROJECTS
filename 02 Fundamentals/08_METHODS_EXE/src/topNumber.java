import java.util.Arrays;
import java.util.Scanner;

public class topNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());


        //•	Its sum of digits is divisible by 8, e.g. 8, 16, 88.
        //•	Holds at least one odd digit, e.g. 232, 707, 87578.

        topPrint(findTop(findOddDigit(n)));

    }

    private static void topPrint(String topResult) {
        int [] numbers = Arrays.stream(topResult.split(" ")).mapToInt(Integer::parseInt).toArray();//сплитвам числата който се делят на 8 и в които има нечетна цифра

        for (int topN:numbers) {//обхождам всяко едно число от този масив и го принтирам

            System.out.println(topN);

        }
    }

    private static String findTop(String oddDigit) {
        int[] numbers = Arrays.stream(oddDigit.split(" ")).mapToInt(Integer::parseInt).toArray();//сплитвам числата, в които има мечетна цифра
        StringBuilder topResult = new StringBuilder();

        for(int number : numbers){ //обхождам всяко едно от числата в който има нечетна цифра и проверявам, дали нялкое от тях се дели на 8
            int g = number;
            int sum = 0;

            while (g != 0){ // докато не стигна до 0
                int digits = g % 10; // взимам последната цифра
                sum += digits; // добавям я в сумата
                g /= 10; // махам я и взимам следващата
            }
            if (sum % 8 == 0){ // ако сумата се дели на 8 без остатък!!!! добавям числото в топ числата
                topResult.append(number).append(" ");//

            }
        }
        return topResult.toString();

    }

    private static String findOddDigit(int n) { // 1. първо проверявам в кои числа има поне една нечетна цифра

        StringBuilder oddDigitResult = new StringBuilder();
        for (int i = 1; i <= n ; i++) {
            int g = i;

            while (g != 0){
                int digit = g % 10;//взимам последното число
                if (digit % 2 == 1) { // проверявам дали е нечетно
                    oddDigitResult.append(i).append(" ");
                    // тези които отговарят ги добавям в сумата

                    break;
                } else {
                    g /= 10; // ако не е нечетно, го махам и отивам в следващото и така докато не обходя всички

                }

            }

        }  return oddDigitResult.toString();//преобразувам в стринг и връщам

    }
}




