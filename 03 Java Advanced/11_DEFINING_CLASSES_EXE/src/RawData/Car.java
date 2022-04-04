package RawData;

import java.util.ArrayList;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private  ArrayList<Tire> tire;

    public Car(String model, Engine engine, Cargo cargo, ArrayList<Tire> tire) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tire = tire;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Tire> getTire() {
        return tire;
    }

    public void setTire(ArrayList<Tire> tires) {
        this.tire = tires;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", cargo=" + cargo +
                ", tire=" + tire +
                '}';
    }
}
