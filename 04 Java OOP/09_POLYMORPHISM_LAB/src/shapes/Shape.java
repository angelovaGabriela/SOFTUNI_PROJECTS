package shapes;

public abstract class Shape {
private double area;
private double perimeter;

    protected void setArea(double area) {
        this.area = area;
    }

    protected void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

public abstract double calculatePerimeter();
public abstract double calculateArea();


    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }
}

