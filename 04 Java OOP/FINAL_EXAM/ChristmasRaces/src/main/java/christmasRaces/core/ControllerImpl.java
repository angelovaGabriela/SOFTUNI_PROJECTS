package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.BaseCar;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.CarRepository;
import christmasRaces.repositories.interfaces.DriverRepository;
import christmasRaces.repositories.interfaces.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Objects;

public class ControllerImpl implements Controller {
    Repository<Driver> driverRepository;
    Repository<Car> carRepository;
    Repository<Race> raceRepository;


    public ControllerImpl(Repository<Driver> driverRepository,
                          Repository<Car> carRepository,
                          Repository<Race> raceRepository) {
        this.driverRepository = new DriverRepository();
        this.carRepository = new CarRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createDriver(String driverName ) {
        Driver driver = new DriverImpl(driverName);

        if (driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName);

    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = null;
        if (type.equals("MuscleCar")) {
            car = new MuscleCar(model, horsePower);
        } else if (type.equals("SportsCar")) {
           car = new SportsCar(model, horsePower);
        }

        if (carRepository.getAll().contains(car)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS,model));
        }
        return String.format(OutputMessages.CAR_CREATED, type, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);

      if (!driverRepository.getAll().contains(driver)) {
          throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
      } else if (!carRepository.getAll().contains(car)) {
          throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,carModel));
      }

      driver.addCar(car);

      return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        
    }

    @Override
    public String startRace(String raceName) {
        return null;
    }

    @Override
    public String createRace(String name, int laps) {
        return null;
    }
}

