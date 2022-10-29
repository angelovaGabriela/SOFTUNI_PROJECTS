package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importJSON.ImportPicturesDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

    private final static Path path = Path.of("src","main", "resources", "files", "json", "pictures.json");

    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository,
                              CarRepository carRepository,
                              Gson gson, Validator validator,
                              ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readPicturesFromFile();
        ImportPicturesDTO[] pictures = this.gson.fromJson(json, ImportPicturesDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportPicturesDTO importPicture : pictures) {

            Set<ConstraintViolation<ImportPicturesDTO>> validationErrors =
                    this.validator.validate(importPicture);

            if (validationErrors.isEmpty()) {
                Optional<Picture> optionalPicture = this.pictureRepository.findByName(importPicture.getName());
                if (optionalPicture.isEmpty()) {
                    Picture picture = this.modelMapper.map(importPicture, Picture.class);
                    Optional<Car> car = this.carRepository.findById(importPicture.getCar());

                    picture.setCar(car.get());
                    this.pictureRepository.save(picture);

                    String message = String.format("Successfully imported picture %s", picture.getName());
                    result.add(message);

                } else {
                    result.add("Invalid picture");
                }

            } else {
                result.add("Invalid picture");
            }
        }
        return String.join("\n", result);
    }
}
