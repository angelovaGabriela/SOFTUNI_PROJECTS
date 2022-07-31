package catHouse.entities.cat;

import catHouse.entities.houses.LongHouse;

public class LonghairCat extends BaseCat {
    private LongHouse house;

    private static int  INITIAL_KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public int getKilograms() {
        return INITIAL_KILOGRAMS;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void eating() {
        INITIAL_KILOGRAMS ++;
    }
}
