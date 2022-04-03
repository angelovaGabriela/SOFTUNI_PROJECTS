import java.util.Arrays;
import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int [] number =  Arrays.stream(scanner.nextLine().split(" "))
               .mapToInt(e-> Integer.parseInt(e))//всеки един елемент искаме да го парснеме към инт
               .toArray();
       boolean isFound = false;
        for (int index = 0; index < number.length ; index++) {
            int element = number[index];
            int leftSum = 0;
            int rightSum = 0;
            for (int leftIndex = 0; leftIndex < index ; leftIndex++) {
                leftSum+= number[leftIndex];

            }
            for (int rightIndex = index + 1; rightIndex <= number.length-1; rightIndex++) {
               rightSum+=number[rightIndex];
            }
            if(leftSum == rightSum){
                System.out.println(index);
                isFound=true;
            }

            } if (!isFound){
            System.out.println("no");

        }


    }
}
