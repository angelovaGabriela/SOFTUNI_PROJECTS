import java.util.Scanner;
import java.util.function.DoublePredicate;

public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String figure = scanner.nextLine();

        if (figure.equals("square")){

            double side = Double.parseDouble(scanner.nextLine());
            double faceSquare = side * side;

            System.out.printf("%.3f", faceSquare);
        }

        if (figure.equals("rectangle")){

            double sideOne =Double.parseDouble(scanner.nextLine());
            double sideTwo = Double.parseDouble(scanner.nextLine());
            double faceRectangle = sideOne * sideTwo;

            System.out.printf("%.3f", faceRectangle);
            }
        if (figure.equals("circle")){

            double r = Double.parseDouble(scanner.nextLine());
            double faceCircle = Math.PI * Math.pow(r,2);

            System.out.printf("%.3f", faceCircle);
        }
        if (figure.equals("triangle")){
            double side = Double.parseDouble(scanner.nextLine());
            double height = Double.parseDouble(scanner.nextLine());
            double faceTriangle = 0.5 * side * height;

            System.out.printf("%,3f", faceTriangle);

        }
        }
    }

