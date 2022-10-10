package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.dto.importCities.ImportCitiesDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "cities.json");

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }



    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCities() throws IOException {
        String json = this.readCitiesFileContent();
        ImportCitiesDTO[] cities = this.gson.fromJson(json, ImportCitiesDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportCitiesDTO importCity : cities) {

            Set<ConstraintViolation<ImportCitiesDTO>> validationErrors
                    = this.validator.validate(importCity);
            if (validationErrors.isEmpty()) {
                Optional<City> optionalCity =
                        this.cityRepository
                                .findByCityName(importCity.getCityName());
                if (optionalCity.isPresent()) {
                    result.add("Invalid city");
                } else {
                    City city = this.modelMapper.map(importCity, City.class);
                    this.cityRepository.save(city);

                    String message
                            = String.format("Successfully imported city %s - %d", city.getCityName(), city.getPopulation());

                    result.add(message);

                }
             } else {
                result.add("Invalid city");
            }
        }
        return String.join("\n", result);
    }
}
