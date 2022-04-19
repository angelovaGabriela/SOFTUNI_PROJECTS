package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;
import zoo.repositories.FoodRepository;

import java.util.*;

import static zoo.common.ExceptionMessages.AREA_NAME_NULL_OR_EMPTY;
import static zoo.common.ExceptionMessages.NOT_ENOUGH_CAPACITY;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;
    private List<Food> foods;
    private List<Animal> animals;

    protected BaseArea(String name, int capacity) {
        setName(name);
        setCapacity(capacity);
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();


    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
           throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
             }

        this.name = name;
    }

    private void setCapacity(int capacity) {
       this.capacity = capacity;
    }

    @Override
    public String getName() {
       return name;
    }

    @Override
    public List<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public List<Food> getFoods() {
       return this.foods;
    }

    @Override
    public int sumCalories() {

        int sumCalories = 0;

        for (Food food : foods) {
            int calories = food.getCalories();
            sumCalories += calories;
        }
       return sumCalories;
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() < this.capacity){
            this.animals.add(animal);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removeAnimal(Animal animal) {
       if (animals.contains(animal)) {
        this.animals.remove(animal);
    }
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {

        // TODO: Implementation
    }

    @Override
    public String getInfo() {
        return null;
        //TODO: Print the info
    }
}
