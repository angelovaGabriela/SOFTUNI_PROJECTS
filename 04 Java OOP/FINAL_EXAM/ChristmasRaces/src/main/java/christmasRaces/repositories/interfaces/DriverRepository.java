package christmasRaces.repositories.interfaces;

import christmasRaces.entities.cars.Car;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DriverRepository implements Repository<Driver> {

    private final Collection<Driver> models;

    public DriverRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {

        return this.models
                .stream()
                .filter(driver -> driver.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Driver> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Driver model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        return this.models.remove(model);
    }
}