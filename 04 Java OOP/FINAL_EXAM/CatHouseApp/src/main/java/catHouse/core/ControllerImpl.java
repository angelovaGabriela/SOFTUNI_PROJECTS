package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;

import catHouse.repositories.ToyRepository;


import java.util.ArrayList;
import java.util.Collection;



public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private final Collection<House> houses;

    public ControllerImpl() {
        this.houses = new ArrayList<>();
        this.toys = new ToyRepository();

    }


    @Override
    public String addHouse(String type, String name) {
        if (type.equals("ShortHouse")){
            House shortHouse = new ShortHouse(name);
            this.houses.add(shortHouse);
        } else if (type.equals("LongHouse")) {
            House longHouse = new LongHouse(name);
            this.houses.add(longHouse);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);

    }

    @Override
    public String buyToy(String type) {

      if (type.equals("Ball")) {
          Toy ball = new Ball();
          this.toys.buyToy(ball);
      } else if (type.equals("Mouse")) {
          Toy mouse = new Mouse();
          this.toys.buyToy(mouse);
      } else {
          throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
      }

      return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toy = this.toys.findFirst(toyType);

        if (toy == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        } else {
             House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findAny().get();
             house.buyToy(toy);
             this.toys.removeToy(toy);

             return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
        }
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findAny().get();
        Cat cat;

        if (catType.equals("ShorthairCat")) {
            cat = new ShorthairCat(catName, catBreed, price);

            if (house.getClass().getSimpleName().equals("ShortHouse")) {
                house.addCat(cat);
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
            } else {
                return ConstantMessages.UNSUITABLE_HOUSE;
            }
        } else if (catType.equals("LonghairCat")) {
            cat = new LonghairCat(catName, catBreed, price);

            if (house.getClass().getSimpleName().equals("LongHouse")) {
                house.addCat(cat);
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
            } else {
                return ConstantMessages.UNSUITABLE_HOUSE;
            }
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }







    }

    @Override
    public String feedingCat(String houseName) {
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findAny().get();
        house.feeding();

        int fedCount = house.getCats().size();

        return String.format(ConstantMessages.FEEDING_CAT, fedCount);


    }

    @Override
    public String sumOfAll(String houseName) {
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findAny().get();
       double sumOfAll = 0;
        for (Toy toy : house.getToys()) {
            double currentToyPrice = toy.getPrice();
            sumOfAll += currentToyPrice;
        }

        for (Cat cat : house.getCats()) {
            double currentCatPrice = cat.getPrice();
            sumOfAll += currentCatPrice;
        }

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, sumOfAll);

    }

    @Override
    public String getStatistics() {
        String stats = null;
    for (House house : this.houses) {
          stats = house.getStatistics();
        }
      return stats;
    }

    private void setToys(ToyRepository toys) {
        this.toys = toys;
    }
}
