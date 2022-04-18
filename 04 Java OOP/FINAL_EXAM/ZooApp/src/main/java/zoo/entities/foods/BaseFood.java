package zoo.entities.foods;

public abstract class BaseFood implements Food {

    private int calories;
    private double price;

    protected BaseFood(int calories, double price) {
        this.calories = calories;
        this.price = price;
    }


    private void setCalories(int calories) {
        this.calories = calories;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
