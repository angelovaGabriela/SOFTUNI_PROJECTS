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

import java.util.*;


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


        // взимам всички шофьори от това състезание
        Collection<Driver> allDrivers = race.getDrivers();

        // колекция за най-добри резултати
        List<Double> bestPoints = new ArrayList<>();

        // итерирам през шофьорите от състезанието
        for (Driver driver : allDrivers) {
         // взимам им спечелените точки
         double points = driver.getCar().calculateRacePoints(race.getLaps());
         // добавям ги в колекцията с най-добри резултати
         bestPoints.add(points);
        }

        // сортирам ги в descending order -> highest to lowest
        bestPoints.sort(Collections.reverseOrder());

        // създавам колекция за първите трима
        List<Driver> firstThree = new ArrayList<>();

        // итерирам през съзтезателите в състезанието
        for (Driver winner : race.getDrivers()) {

            // взимам резултата на всеки състезател
            double best = winner.getCar().calculateRacePoints(race.getLaps());

            // ако резултата му е равен на първият от bestPoints,
            // го взимам и го поставям на първа позиция в листа firstThree
            // така и за останалите двама
            if (best == bestPoints.get(0)) {
                firstThree.set(0,winner);
            } else if (best == bestPoints.get(1)) {
                firstThree.set(1,winner);
            }  else if (best == bestPoints.get(2)) {
                firstThree.set(2, winner);
            }
        }

        String nameOfFirst = firstThree.get(0).getName();
        String nameOfSecond = firstThree.get(1).getName();
        String nameOfThird = firstThree.get(2).getName();

        this.raceRepository.remove(race);
        //string builder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, nameOfFirst,raceName)).append(System.lineSeparator())
                        .append(String.format(OutputMessages.DRIVER_SECOND_POSITION, nameOfSecond,raceName)).append(System.lineSeparator())
                .append(String.format(OutputMessages.DRIVER_SECOND_POSITION, nameOfThird,raceName));

        return stringBuilder.toString();
    }
}

