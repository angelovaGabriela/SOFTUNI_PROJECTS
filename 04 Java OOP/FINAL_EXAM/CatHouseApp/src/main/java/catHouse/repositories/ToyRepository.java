package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.Collection;

public class ToyRepository implements Repository<Toy> {

    private Collection<Toy> toys;
    @Override
    public void buyToy(Toy toy) {

    }

    @Override
    public boolean removeToy(Toy toy) {
        return false;
    }

    @Override
    public Toy findFirst(String type) {
        return null;
    }
}
