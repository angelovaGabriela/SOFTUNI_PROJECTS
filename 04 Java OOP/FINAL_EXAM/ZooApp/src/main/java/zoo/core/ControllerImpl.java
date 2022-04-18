package zoo.core;

import zoo.common.ConstantMessages;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;

import java.util.List;

import static zoo.common.ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE;
import static zoo.common.ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE;
import static zoo.common.ExceptionMessages.INVALID_AREA_TYPE;
import static zoo.common.ExceptionMessages.INVALID_FOOD_TYPE;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private List<Area> areas;

    public ControllerImpl() {
    }


    @Override
    public String addArea(String areaType, String areaName) {
        if (areaType.equals("WaterArea") || areaType.equals("LandArea")) {
            Area area = areaType.equals("WaterArea")
                    ? new WaterArea(areaName)
                    : new LandArea(areaName);

            this.areas.add(area);
            return String.format(SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
        }
        throw new NullPointerException(INVALID_AREA_TYPE);
    }

    @Override
    public String buyFood(String foodType) {
        if (foodType.equals("Vegetable") || foodType.equals("Meat")) {
            Food food = foodType.equals("Vegetable")
                    ? new Vegetable()
                    : new Meat();
            this.foodRepository.add(food);
            return String.format(SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
        }
      throw new IllegalArgumentException(INVALID_FOOD_TYPE);
    }
    @Override
    public String foodForArea(String areaName, String foodType) {

        Food food = this.foodRepository.findByType(foodType);

        if (!(food == null)) {

            Area area = areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().get();
            area.addFood(food);
            boolean remove = this.foodRepository.remove(food);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);


        }

            throw new IllegalArgumentException(
                    String.format("There isn't a food of type %s.",
                            foodType));


    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        return null;
    }

    @Override
    public String feedAnimal(String areaName) {
        return null;
    }

    @Override
    public String calculateKg(String areaName) {
        return null;
    }

    @Override
    public String getStatistics() {
        return null;
    }
}
