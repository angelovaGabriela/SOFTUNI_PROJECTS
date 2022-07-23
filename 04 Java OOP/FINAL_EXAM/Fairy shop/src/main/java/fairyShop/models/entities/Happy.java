package fairyShop.models.entities;


import fairyShop.models.Instrument;


public class Happy extends BaseHelper {

    private static final int ENERGY = 100;

    public Happy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {

    }

    @Override
    public void addInstrument(Instrument instrument) {

    }

    @Override
    public boolean canWork() {
        return false;
    }
}
