package carShop;

public class Seat extends CarImplementation implements Sellable{
    // всичките ми полета са private final - не искам достъп от външния свят и не искам да ги променям
    private final Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
    super(model, color, horsePower, countryProduced);
        this.price = price;

    }


    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() { // използвам базовия то стринф и използвам супер
        return super.toString() + System.lineSeparator()
             + getModel()  + " sells for " + price;
    }
}
