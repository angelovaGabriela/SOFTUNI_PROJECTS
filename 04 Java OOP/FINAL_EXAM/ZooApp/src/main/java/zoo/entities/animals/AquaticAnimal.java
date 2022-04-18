package zoo.entities.animals;

import zoo.entities.areas.WaterArea;

public class AquaticAnimal extends BaseAnimal {

    public AquaticAnimal(String name, String kind,double price) {
        super(name, kind, 2.50, price);
    }

    @Override
    public void eat() {
        double kilograms = this.getKg();
        kilograms += 7.50;
    }
}
