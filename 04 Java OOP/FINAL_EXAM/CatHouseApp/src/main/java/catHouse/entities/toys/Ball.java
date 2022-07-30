package catHouse.entities.toys;

public class Ball extends BaseToy {
    //The ball has a softness of 1 and a price of 10.
    private static final int SOFTNESS = 1;
    private static final double PRICE = 10;

    public Ball() {
        super(SOFTNESS, PRICE);
    }

    @Override
    public int getSoftness() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
