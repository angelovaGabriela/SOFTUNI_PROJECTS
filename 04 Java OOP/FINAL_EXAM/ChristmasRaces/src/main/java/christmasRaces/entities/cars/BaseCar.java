package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.races.Race;

import java.util.Objects;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;



    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
       this.setModel(model);
       this.setHorsePower(horsePower);
    }

    @Override
    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
      if (model.trim().isEmpty() || model.length() < 4) {
          throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL, model, 4));
      }
      this.model = model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double calculateRacePoints(int laps) {
        double points = this.cubicCentimeters / this.horsePower;

        return points * laps;
    }

    private void setHorsePower(int horsePower) {
      if (Objects.equals(BaseCar.class.getSimpleName(), "MuscleCar")) {
          if (horsePower < 400 || horsePower > 600) {
              throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
          } else {
              this.horsePower = horsePower;
          }
      } else if (Objects.equals(BaseCar.class.getSimpleName(), "SportsCar")) {
          if (horsePower < 250 || horsePower > 450) {
              throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,horsePower));
          } else {
              this.horsePower = horsePower;
          }
      }
    }
}
