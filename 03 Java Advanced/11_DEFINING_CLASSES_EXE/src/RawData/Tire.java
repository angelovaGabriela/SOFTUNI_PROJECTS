package RawData;

public class Tire {

    private double pressure;
    private int age;

    public Tire(double pressure, int age) {
        this.pressure = pressure;
        this.age = age;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "pressure=" + pressure +
                ", age=" + age +
                '}';
    }
}


//{Tire1Pressure} {Tire1Age}
// {Tire2Pressure} {Tire2Age}
// {Tire3Pressure} {Tire3Age}