package christmasRaces.repositories.interfaces;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {

    private Collection<Car> models;

    public CarRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        return null;
    }

    @Override
    public Collection<Car> getAll() {
        return null;
    }

    @Override
    public void add(Car model) {

    }

    @Override
    public boolean remove(Car model) {
        return false;
    }
}
