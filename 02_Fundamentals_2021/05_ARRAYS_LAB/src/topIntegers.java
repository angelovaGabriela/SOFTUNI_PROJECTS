import java.util.Arrays;
import java.util.Scanner;

public class topIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String input = scanner.nextLine();
        int [] numbers = Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        //обхождам всеки индекс
        for (int i = 0; i <= numbers.length - 1; i++) {

            boolean topNumber = true;
// обхождам всеки индекс + 1 и сравнявам числото което лежи там с предното
            for (int j = i+1; j <= numbers.length-1; j++) {

                if(numbers[i] <= numbers[j]){
                    topNumber = false;
                    break;

                }

            }

            if (topNumber){// ако е по-голямо от предишното се отпечатва
                System.out.print(numbers[i] + " ");
            }
        }




        }



        }

