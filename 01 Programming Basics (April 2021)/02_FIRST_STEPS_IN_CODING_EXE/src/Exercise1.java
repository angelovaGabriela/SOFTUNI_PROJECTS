import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double usd = Double.parseDouble(scanner.nextLine());
        Double bgl = usd * 1.79549;
        System.out.println(bgl);
    }
}
