package catHouse.entities.cat;


public class LonghairCat extends BaseCat {


    private static int  INITIAL_KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public int getKilograms() {
        return INITIAL_KILOGRAMS;
    }


    @Override
    public void eating() {
        INITIAL_KILOGRAMS += 1;
    }
}
