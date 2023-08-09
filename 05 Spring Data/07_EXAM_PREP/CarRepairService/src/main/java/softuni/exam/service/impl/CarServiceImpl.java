package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.dto.car.ImportCarsDTO;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CarServiceImpl implements CarService {
    private final static Path CARS_FILE_PATH = Path.of("src", "main", "resources", "files", "xml", "cars.xml");

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ModelMapper modelMapper,
                          Validator validator) throws JAXBException {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ImportCarsDTO.class);
        this.unmarshaller = context.createUnmarshaller();

    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
       return Files.readString(CARS_FILE_PATH);
    }

    @Override
    public String importCars() throws IOException, JAXBException {


    return null;

    }
}
