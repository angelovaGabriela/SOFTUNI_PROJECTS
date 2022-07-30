package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar {

    private static final double CUBIC_CENTIMETERS = 5000;


    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
        }
    }



    @Override
    public double getCubicCentimeters() {
        return CUBIC_CENTIMETERS;
    }
}
