import java.util.Scanner;

public class EvenPowersOf2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
int n = Integer.parseInt(scanner.nextLine());

        for (int power = 0; power <= n; power+=2){
        System.out.printf("%.0f%n", Math.pow(2,power));
        }
    }
}
