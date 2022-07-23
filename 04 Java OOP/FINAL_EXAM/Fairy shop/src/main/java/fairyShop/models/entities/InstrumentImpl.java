package fairyShop.models.entities;

import fairyShop.models.Instrument;

public class InstrumentImpl implements Instrument {

    private int power;

    public InstrumentImpl(int power) {
        this.power = power;
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public void use() {

    }

    @Override
    public boolean isBroken() {
        return false;
    }

    private InstrumentImpl setPower(int power) {
        this.power = power;
        return this;
    }
}
