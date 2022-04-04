package Problem3;

public class Car {
    private String model;
    private double fuelAmount;
    private double consumption;
    private double distanceTravelled;

    //конструктор
    // без distance travelled в конструктура,
    // защото в задачата се казва,
    // че първоначалната му стойност винги == 0

    public Car(String model, double fuelAmount, double consumption) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.consumption = consumption;
        this.distanceTravelled = 0;
    }

    //дали колата може да измине съответната дистанция или не
    public boolean drive(double kilometersToDrive){
        double fuelRequired = kilometersToDrive * consumption;

        if (fuelRequired > this.fuelAmount){
            return false; // не може да се движи, няма гориво
        } else {
            this.fuelAmount -= fuelRequired; // премахвам използваното гориво
            this.distanceTravelled += kilometersToDrive; //увеличавам пробега на моята кола


            return true; //може да се движи

        }

    }

    @Override
    public String toString() {
        // задавам си формат според изхода от задачката
        return String.format("%s %.2f %.0f", this.model, this.fuelAmount, this. distanceTravelled);
    }
}
