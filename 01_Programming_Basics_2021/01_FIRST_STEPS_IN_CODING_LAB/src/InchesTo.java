import java.util.Scanner;

public class InchesTo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1. инчове - реално число - конзола
        //2.см=инчове * 2.54
        //3. отпечатване
        double inches = Double.parseDouble(scanner.nextLine());
        double cm = inches * 2.54;
        System.out.println(cm);

    }
}
