
import java.util.Arrays;
import java.util.Scanner;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arrays = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(e->Integer.parseInt(e))
                .toArray();

        int length = 1;
        int maxLength =0;

        int startIndex = 0;
        int bestIndex = 0;


        for (int i = 1; i <= arrays.length - 1 ; i++) {
            if (arrays[i] == arrays [i-1]) {
                length ++;
            } else{
                length = 1;
                startIndex = i;

            }
            if(length > maxLength){
                maxLength = length;
                bestIndex = startIndex;

            }

        }
        for (int i = bestIndex; i < bestIndex + maxLength; i++) {
            System.out.print(arrays[i] + " ");
        }

    }
}
