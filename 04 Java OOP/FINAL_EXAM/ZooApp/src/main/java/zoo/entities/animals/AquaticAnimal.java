package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {

    private static double KG = 2.50;
    public AquaticAnimal(String name, String kind,double price) {
        super(name, kind,KG , price);
    }

    @Override
    public void eat() {
        KG += 7.50;

    }
}
