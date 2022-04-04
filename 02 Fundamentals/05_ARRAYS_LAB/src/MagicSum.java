import java.util.Arrays;
import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int [] digits = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int givenNumber = Integer.parseInt(scanner.nextLine());

// 1 7 6 2 19 23
//8
        //all unique pairs in the arrays of integers whose sum is equal to the given number
        for (int i = 0; i <= digits.length-1; i++) {
            for (int j = i+1; j <= digits.length-1 ; j++) {

                if(digits[i] + digits[j] == givenNumber){

                    System.out.println(digits[i] + " " + digits[j] + " ");
                }

                }


            }
        }
    }







