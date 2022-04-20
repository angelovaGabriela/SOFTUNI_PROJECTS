package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {
private static double KG = 5.50;
    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KG, price);
    }

    @Override
    public void eat() {
      KG += 5.70;
    }
}
