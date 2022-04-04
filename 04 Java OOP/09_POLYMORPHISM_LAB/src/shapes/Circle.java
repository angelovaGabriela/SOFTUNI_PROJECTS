package shapes;

public class Circle extends Shape{
    private final double radius;


    public Circle(double radius) {
        this.radius = radius;
    }

    public final double  getRadius() {
        return radius;
    }

    @Override
    public double calculatePerimeter() {
       super.setPerimeter(2 * Math.PI * this.radius);

    return super.getPerimeter();
    }

    @Override
    public double calculateArea() {
        super.setArea(Math.PI * this.radius * this.radius);

    return super.getArea();
    }
}
