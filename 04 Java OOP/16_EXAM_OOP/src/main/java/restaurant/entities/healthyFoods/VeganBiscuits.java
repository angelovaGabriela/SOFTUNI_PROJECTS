package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food {
    private static final int InitialVeganBiscuitPortion =  205;

    public VeganBiscuits(String name, double price) {
        super(name, InitialVeganBiscuitPortion, price);
    }
}
