package fairyShop.models.entities;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.Instrument;

public class InstrumentImpl implements Instrument {

    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        this.setPower(this.getPower()-10);
        if (this.power < 0) {
           this.power = 0;
        }
    }

    @Override
    public boolean isBroken() {
        return this.power == 0;
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }
}
