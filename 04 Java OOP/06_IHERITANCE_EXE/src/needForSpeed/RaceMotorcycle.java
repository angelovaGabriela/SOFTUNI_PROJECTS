package needForSpeed;

public class RaceMotorcycle extends Motorcycle{
    private final static double DEFAULT_FUEL_CONSUMPTION = 8; //дефоутна стойност за дадения клас

    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        this.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
