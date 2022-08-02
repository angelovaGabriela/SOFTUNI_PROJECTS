package catHouse.entities.cat;

import catHouse.entities.houses.ShortHouse;

public class ShorthairCat extends BaseCat {

    private static int  INITIAL_KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);

    }


    @Override
    public int getKilograms() {
        return INITIAL_KILOGRAMS;
    }


    @Override
    public void eating() {
        INITIAL_KILOGRAMS += 3;
    }
}
