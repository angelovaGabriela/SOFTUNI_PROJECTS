package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.dto.car.CarImportDTO;
import softuni.exam.models.dto.car.ImportCarsDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarsServiceImpl implements CarService {
    private static final Path CARS_FILE_PATH = Path.of("src", "main", "resources", "files", "xml", "cars.xml");

    private final CarsRepository carsRepository;

    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public CarsServiceImpl(CarsRepository carsRepository,
                           ModelMapper modelMapper,
                           Validator validator) throws JAXBException {
        this.carsRepository = carsRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ImportCarsDTO.class);
        this.unmarshaller = context.createUnmarshaller();

    }

    @Override
    public boolean areImported() {
        return this.carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
       return Files.readString(CARS_FILE_PATH);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        ImportCarsDTO importCarsDTO = (ImportCarsDTO) this.unmarshaller.unmarshal(new FileReader(CARS_FILE_PATH.toAbsolutePath().toString()));
        List<String> result = new ArrayList<>();


        for (CarImportDTO carImportDTO : importCarsDTO.getCars()) {
            Set<ConstraintViolation<CarImportDTO>> validationErrors
                    = this.validator.validate(carImportDTO);

       if (validationErrors.isEmpty()) {
           Optional<Car>  optionalCar = this.carsRepository.findByPlateNumber(carImportDTO.getPlateNumber());
            if(optionalCar.isPresent()) {
                result.add("Invalid car");
            } else {
                Car car = this.modelMapper.map(carImportDTO, Car.class);
                this.carsRepository.save((car));
                String message = String.format("Successfully imported car %s – %s",
                       car.getCarMake(), car.getCarModel());

                result.add(message);
            }

       } else {
           result.add("Invalid car");
       }
        }
        return String.join("\n", result);

    }
}
