package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseHouse implements House {

    private String name;
    private final int capacity;
    private final Collection<Toy> toys;
    private final Collection<Cat> cats;

    protected BaseHouse(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        int softnessSum = 0;
        for (Toy toy: this.toys) {
            int currentSoftness = toy.getSoftness();
            softnessSum += currentSoftness;
        }
       return softnessSum;
    }

    @Override
    public void addCat(Cat cat) {

        if(this.cats.size() < this.capacity) {
            this.cats.add(cat);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
    }

    @Override
    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : this.cats) {
            cat.eating();
        }
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s:",
                this.name,
                this.getClass().getSimpleName())).append(System.lineSeparator());

        builder.append("Cats: ");

        if (this.cats.isEmpty()) {
            builder.append("none");
        } else {
            this.cats.forEach(cat -> builder.append(cat.getName()).append(" "));
        }

        builder.append(System.lineSeparator());
        builder.append("Toys: %d Softness: %d", this.toys.size(), sumSoftness());

        return builder.toString();


        //  StringBuilder builder = new StringBuilder();
        //        builder.append("The grooming salon has the following clients:").append(System.lineSeparator());
        //        data.forEach(pet -> builder.append(pet.getName()).append(" ").append(pet.getOwner()).append(System.lineSeparator()));
        //
        //        return builder.toString();
        //    }
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw  new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Cat> getCats() {
        return Collections.unmodifiableCollection(this.cats);
    }

    @Override
    public Collection<Toy> getToys() {
        return Collections.unmodifiableCollection(this.toys);
    }


}
