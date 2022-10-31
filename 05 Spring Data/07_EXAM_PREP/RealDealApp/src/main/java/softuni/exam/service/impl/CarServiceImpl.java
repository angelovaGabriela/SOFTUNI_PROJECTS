package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importJSON.ImportCarsDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final static Path path = Path.of("src","main", "resources", "files", "json", "cars.json");
    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          Gson gson,
                          ModelMapper modelMapper,
                          Validator validator) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return  Files.readString(path);
    }

    @Override
    public String importCars() throws IOException {
        String json = this.readCarsFileContent();
        ImportCarsDTO[] cars = this.gson.fromJson(json, ImportCarsDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportCarsDTO car : cars) {
            Set<ConstraintViolation<ImportCarsDTO>> validationErrors
                    = this.validator.validate(car);

            if (validationErrors.isEmpty()) {
                Optional<Car> optionalCar = this.carRepository
                        .findByMakeAndModelAndKilometers(car.getMake(), car.getModel(), car.getKilometers());

                if (optionalCar.isPresent()) {
                    result.add("Invalid car");
                } else {
                    Car carToSave = this.modelMapper.map(car, Car.class);
                    this.carRepository.save(carToSave);

                    String message = String.format("Successfully imported car %s - %s", carToSave.getMake(), carToSave.getModel());
                    result.add(message);
                }
            } else {
                result.add("Invalid car");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {

        return this.carRepository.getAllCarsOrderByPicturesCountDescThenByMakeAsc().stream()
                .map(Car::toString).collect(Collectors.joining("\n"));

    }
}
