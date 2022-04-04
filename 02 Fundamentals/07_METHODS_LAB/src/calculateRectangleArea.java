import java.util.Scanner;

public class calculateRectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double width = Double.parseDouble(scanner.nextLine());
        double length = Double.parseDouble(scanner.nextLine());

        double area = getRectangleArea(width,length); // изваждам формулката в метода за да ми е по-прегледен кода
        System.out.printf("%.0f", area);
    }

    private static double getRectangleArea(double width, double length) {
    double rectangleArea = width * length;
    return rectangleArea;
    }
}
