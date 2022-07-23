package fairyShop.models;


import fairyShop.models.entities.BaseHelper;

public class Sleepy extends BaseHelper {

    private static final int ENERGY = 50;

    public Sleepy(String name) {
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
