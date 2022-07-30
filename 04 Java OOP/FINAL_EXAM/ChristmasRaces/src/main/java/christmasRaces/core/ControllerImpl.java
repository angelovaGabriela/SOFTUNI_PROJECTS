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


import java.util.*;


public class ControllerImpl implements Controller {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final RaceRepository raceRepository;



    public ControllerImpl(DriverRepository driverRepository, CarRepository carRepository, RaceRepository raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        Driver driver = new DriverImpl(driverName);
        Driver byName = this.driverRepository.getByName(driverName);



        if (this.driverRepository.getAll().contains(byName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driverName));
        }

        this.driverRepository.add(driver);
        return String.format(OutputMessages.DRIVER_CREATED, driverName);

    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        if (this.carRepository.getAll().contains(this.carRepository.getByName(model))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        } else {
            Car car = type.equals("Muscle")
                    ? new MuscleCar(model, horsePower)
                    : new SportsCar(model,horsePower);
            this.carRepository.add(car);
            return String.format(OutputMessages.CAR_CREATED, type, model);
        }

    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);
        Car car = this.carRepository.getByName(carModel);

        if (!this.driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        } else if (!this.carRepository.getAll().contains(car)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }

        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);

        if (!this.raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (!this.driverRepository.getAll().contains(driver)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }

        race.addDriver(driver);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);

        if (this.raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);

        if (!this.raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        } else if (race.getDrivers().size() < 3) {
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
                firstThree.add(winner);
            } else if (best == bestPoints.get(1)) {
                firstThree.add(winner);
            }  else if (best == bestPoints.get(2)) {
                firstThree.add(winner);
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
                .append(String.format(OutputMessages.DRIVER_THIRD_POSITION, nameOfThird,raceName));

        return stringBuilder.toString();
    }
}

