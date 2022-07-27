package christmasRaces.entities.cars;

public class SportsCar extends BaseCar {

    private static final double CUBIC_CENTIMETERS = 3000;


    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }


    @Override
    public double getCubicCentimeters() {
        return CUBIC_CENTIMETERS;
    }
}
