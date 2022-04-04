package Problem2Car;

import java.util.Objects;

public class Car {
  private   String brand;
   private String model;
    private int horsPower;


public Car (String brand, String model,int horsePower) {
    this.brand = brand; // this -> референцията на текущия обект, който създавам
    this.model = model;
    this.horsPower = horsePower;
} // конструктор - създава базовата дефинияция на обекта

    public Car(String brand, String model){
    this(brand, model, -1);
    }
    public Car(String brand, int horsPower){
    this(brand, "unknown", horsPower);
    }

    public Car(String brand){
    this(brand, "unknown", -1);
    }

public String getBrand(){
    return brand;
}
public String getModel(){
    return model;
}

public int getHorsPower(){
    return horsPower;
}

//setter

    public void setHorsPower(int horsPower) {

    this.horsPower = horsPower;
   }

@Override // предпазва ме да допусна грешка
public  String toString() {
    return  String.format("The car is: %s %s - %d HP.", this.getBrand(), this.getModel(), this.getHorsPower());

}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return horsPower == car.horsPower && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, horsPower);
    }
}
