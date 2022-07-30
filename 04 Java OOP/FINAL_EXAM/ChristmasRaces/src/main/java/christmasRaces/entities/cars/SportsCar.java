package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar {

    private static final double CUBIC_CENTIMETERS = 3000;


    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }

    }


    @Override
    public double getCubicCentimeters() {
        return CUBIC_CENTIMETERS;
    }
}
