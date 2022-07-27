package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.CarRepository;
import christmasRaces.repositories.interfaces.DriverRepository;
import christmasRaces.repositories.interfaces.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
    public String createDriver(String driverName) {
        Driver driver = new DriverImpl(driverName);

        if (driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }
        driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);

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
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
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
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);

        if (!raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (!driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);

        if (raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);

        if (!raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        } else if (raceRepository.getAll().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }



        //TODO: arrange all Drivers and then return the three fastest Drivers.
        // To do this you should sort all Drivers in descending
        // order by the result of CalculateRacePoints method in the Car object. In the end, if everything is valid remove this Race from the race repository

        return null;
        //TODO: return message
    }
}

