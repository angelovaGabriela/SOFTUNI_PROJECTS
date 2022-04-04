package java_advanced_training.parking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Parking {
   private List<Car> data;
   private String type;
   private int capacity;

    public Parking(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }
    public void add(Car car) {
        if (this.data.size() < this.capacity){
            this.data.add(car);
        }
    }
    public boolean remove(String manufacturer, String model){
        int sizeBeforeRemove = data.size(); // проверявам размера преди премахването

        this.data.removeIf(e -> e.getManufacturer().equals(manufacturer) && e.getModel().equals(model));

        return sizeBeforeRemove != data.size(); // ако е премахнат връща true ако не е false
    }
    public Car getLatestCar(){
       if (data.isEmpty()){
           return null;
       } else {
           return this.data.stream().max(Comparator.comparingInt(Car::getYear)).get();

       }

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
        builder.append("The cars are parked in ")
                .append(this.type).append(":").append(System.lineSeparator());

        data.forEach(car -> builder.append(car).append(System.lineSeparator()));

        return builder.toString();
    }




}
