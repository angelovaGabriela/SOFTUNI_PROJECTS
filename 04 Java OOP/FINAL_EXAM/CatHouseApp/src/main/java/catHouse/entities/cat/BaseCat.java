package catHouse.entities.cat;

public abstract class BaseCat implements Cat {

    private String name;
    private String breed;
    private int kilograms;
    private double price;

    protected BaseCat(String name, String breed, double price) {
        this.name = name;
        this.breed = breed;
        this.price = price;
    }

}
