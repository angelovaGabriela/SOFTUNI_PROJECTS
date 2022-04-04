import java.util.Scanner;

public class signOfInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        printSign(number);
    }

    private static void printSign(int number) {
        //когато метода е void не прави нищо
        if(number<0){
            System.out.printf("The number %d is negative.", number);
        } else if (number>0){
            System.out.printf("The number %d is positive.", number);
        } else {
            System.out.printf("The number %d is zero.", number);
        }
    }
}
