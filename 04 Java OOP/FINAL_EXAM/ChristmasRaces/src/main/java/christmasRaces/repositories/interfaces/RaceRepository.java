package christmasRaces.repositories.interfaces;

import christmasRaces.entities.races.Race;

import java.util.ArrayList;
import java.util.Collection;

public class RaceRepository implements Repository<Race> {
private Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return null;
    }

    @Override
    public Collection<Race> getAll() {
        return null;
    }

    @Override
    public void add(Race model) {

    }

    @Override
    public boolean remove(Race model) {
        return false;
    }
}
