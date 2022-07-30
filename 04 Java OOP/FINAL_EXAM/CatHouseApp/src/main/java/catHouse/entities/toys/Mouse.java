package catHouse.entities.toys;

public class Mouse extends BaseToy {

private static final int SOFTNESS = 5;
private static final double PRICE = 15;

    protected Mouse() {
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
