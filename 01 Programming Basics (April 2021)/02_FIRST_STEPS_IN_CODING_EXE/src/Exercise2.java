import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double radians = Double.parseDouble(scanner.nextLine());
                Double degrees = radians * 180 / Math.PI;
        System.out.printf("%.0f", degrees);


    }
}
