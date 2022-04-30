package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import static zoo.common.ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE;
import static zoo.common.ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE;
import static zoo.common.ExceptionMessages.INVALID_AREA_TYPE;
import static zoo.common.ExceptionMessages.INVALID_FOOD_TYPE;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private List<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl(new ArrayList<>());
        this.areas = new ArrayList<>();
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
            this.foodRepository.remove(food);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);


        }

            throw new IllegalArgumentException(
                    String.format("There isn't a food of type %s.",
                            foodType));


    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

      if (animalType.equals("AquaticAnimal") || animalType.equals("TerrestrialAnimal")) {

          Animal animal = animalType.equals("AquaticAnimal")
                  ? new AquaticAnimal(animalName, kind, price)
                  : new TerrestrialAnimal(animalName, kind, price);


          Area area = areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().get();

          int animalsCountBefore = area.getAnimals().size();
          area.addAnimal(animal); // if not added must throw the exception message "Not enough capacity."
          int animalsCountAfter = area.getAnimals().size();

          if (animalsCountBefore != animalsCountAfter) {
              return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
          }
          //AquaticAnimal - WaterArea // TerrestrialAnimal  - LandArea
          if (!(animalType.equals("AquaticAnimal") && area.getClass().getSimpleName().equals("WaterArea")
            || !(animalType.equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("LandArea")))) {
              return ConstantMessages.AREA_NOT_SUITABLE;
          }

      }
      throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
    }

    @Override
    public String feedAnimal(String areaName) {

        Area area = areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().get();
        int fedCount = area.getAnimals().size();
        area.feed();

        return String.format(ConstantMessages.ANIMALS_FED, fedCount);

    }

    @Override
    public String calculateKg(String areaName) {
        // TODO: improve! // expected result on TEST I -> 20 // my result -> 5 // how to reach the implementations for the kilograms of the AquaticAnimal ?

       Area area = areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().get();
       double kgSumOfAllAnimalsInTheArea = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();

      return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, kgSumOfAllAnimalsInTheArea);

    }

    @Override
    public String getStatistics()  {

        StringBuilder statistics = new StringBuilder();
        for (Area area : areas) {
            statistics.append(String.format("%s", area.getInfo()));
            statistics.append(System.lineSeparator());
        }
        return statistics.toString();
    }
}
