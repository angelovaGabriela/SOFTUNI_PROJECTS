package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {

    private static final int  INITIAL_KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getKilograms() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void eating() {

    }
}
