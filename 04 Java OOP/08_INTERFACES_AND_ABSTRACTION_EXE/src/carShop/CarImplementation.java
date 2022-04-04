package carShop;

public class CarImplementation implements Car {

    private final String model;
    private final String color;
    private final Integer horsePower;
    private final String countryProduced;

    public CarImplementation(String model, String color, Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;

    }

    // добавя имплементациите на интерфейса CAR
    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }

    @Override
    public String toString() {
        return "This is " + model + " produced in "
                + countryProduced + " and have "
                + Car.TIRES + " tires";
    }
}
