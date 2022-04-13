package restaurant.entities.healthyFoods;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public  class Food implements HealthyFood {
private String name;
private double portion;
private double price;

    protected Food(String name, double portion, double price) {
      //TODO: add validations with setters
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
