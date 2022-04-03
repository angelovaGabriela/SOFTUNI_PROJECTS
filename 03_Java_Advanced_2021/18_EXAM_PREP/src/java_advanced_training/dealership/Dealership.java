package java_advanced_training.dealership;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dealership {
    public String name;
    public int capacity;
    public List<Car> data;

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();

    }

    public void add(Car car){
        if (data.size() < this.capacity){
            data.add(car);
        }
    }
    public boolean buy(String manufacturer, String model){
       int sizeBefore = data.size();
       data.removeIf(car -> car.getManufacturer().equals(manufacturer) && car.getModel().equals(model));
        return sizeBefore != data.size();
    }

    public Car getLatestCar() {
        if (data.size() == 0){
            return null;
        }
       return this.data.stream().max(Comparator.comparingInt(Car::getYear)).get();
    }

    public Car getCar(String manufacturer, String model){
        for (Car car : data) {
            if (car.getManufacturer().equals(manufacturer) && car.getModel().equals(model)){
                return car;
            }
        }
        return null;
    }

    public int getCount(){
        return data.size();
    }
    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("The cars are in a car java_advanced_training.dealership ").append(name).append(":").append(System.lineSeparator());
        data.stream().forEach(e -> builder.append(e).append(System.lineSeparator()));

        return builder.toString();
    }
}
