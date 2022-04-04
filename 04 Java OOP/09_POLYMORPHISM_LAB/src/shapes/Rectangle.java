package shapes;

public class Rectangle extends Shape{
    private final double width;
    private  final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    @Override
    public double calculatePerimeter() {

           super.setPerimeter(this.width * 2 + this.height * 2);

        return super.getPerimeter();
    }

    @Override
    public double calculateArea() {
        super.setArea(this.width * this.height);

    return super.getArea();
    }

}
