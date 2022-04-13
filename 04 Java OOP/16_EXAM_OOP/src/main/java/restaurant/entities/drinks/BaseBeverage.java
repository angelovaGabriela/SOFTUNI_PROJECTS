package restaurant.entities.drinks;

import restaurant.entities.drinks.interfaces.Beverages;

public abstract class BaseBeverage implements Beverages {
  private String name;
  private int counter;
  private double price;
  private String brand;

    protected BaseBeverage(String name, int counter, double price, String brand) {
        //TODO add validations with setters
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }
}
