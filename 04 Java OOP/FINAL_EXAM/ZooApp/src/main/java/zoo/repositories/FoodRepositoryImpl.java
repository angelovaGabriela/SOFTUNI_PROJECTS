package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRepositoryImpl implements FoodRepository {

    private List<Food> foods;

    public FoodRepositoryImpl(List<Food> foods) {
        this.foods = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
          this.foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        if (foods.contains(food)) {
            foods.remove(food);
            return true;
        }
        return false;
    }

    @Override
    public Food findByType(String type) {
        Food foodByType = this.foods.stream()
                .filter(food -> food.getClass().getSimpleName().equals(type))
                .findFirst()
                .orElse(null);


        return foodByType;
    }
}
