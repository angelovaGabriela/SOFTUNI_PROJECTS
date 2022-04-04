package shapes;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Circle(4);
        Shape shape2 = new Rectangle(13, 27);

        printShape(shape);
        printShape(shape2);

    }

    private static void printShape(Shape shape) {
        System.out.println(shape.calculateArea());
        System.out.println(shape.calculatePerimeter());
    }
}
