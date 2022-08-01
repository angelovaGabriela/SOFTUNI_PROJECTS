package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
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
    private Collection<House> houses;

    public ControllerImpl() {
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        //ShortHouse
        //LongHouse
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
        //Ball
        //Mouse
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
        return null;
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        return null;
    }

    @Override
    public String feedingCat(String houseName) {
        return null;
    }

    @Override
    public String sumOfAll(String houseName) {
        return null;
    }

    @Override
    public String getStatistics() {
        return null;
    }
}
