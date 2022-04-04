package restaurant;

import java.math.BigDecimal;

public class Beverage extends Product {
    private double milliliters; // only in this Class


    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this. milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
