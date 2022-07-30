package catHouse.entities.toys;

public abstract class BaseToy implements Toy {
    private int softness;
    private double price;

    protected BaseToy(int softness, double price) {
        this.softness = softness;
        this.price = price;
    }
}
