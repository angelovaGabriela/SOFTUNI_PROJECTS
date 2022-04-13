package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.Collection;
import java.util.List;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;

    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
      // TODO add setters and validations

    }

    @Override
    public int getTableNumber() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int numberOfPeople() {
        return 0;
    }

    @Override
    public double pricePerPerson() {
        return 0;
    }

    @Override
    public boolean isReservedTable() {
        return false;
    }

    @Override
    public double allPeople() {
        return 0;
    }

    @Override
    public void reserve(int numberOfPeople) {

    }

    @Override
    public void orderHealthy(HealthyFood food) {

    }

    @Override
    public void orderBeverages(Beverages beverages) {

    }

    @Override
    public double bill() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String tableInformation() {
        return null;
    }
}
