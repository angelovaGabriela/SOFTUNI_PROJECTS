package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.importCountries.ImportCountriesDTO;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

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
public class CountryServiceImpl implements CountryService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "countries.json");

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;


    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCountries() throws IOException {
        String json = this.readCountriesFromFile();
        ImportCountriesDTO[] countries = this.gson.fromJson(json, ImportCountriesDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportCountriesDTO importCountry : countries) {

            Set<ConstraintViolation<ImportCountriesDTO>> validationErrors =
                    this.validator.validate(importCountry);

            if (validationErrors.isEmpty()) {

                Optional<Country> optionalCountry
                        = this.countryRepository
                        .findByCountryName(importCountry.getCountryName());

                if (optionalCountry.isPresent()){
                    result.add("Invalid country");
                } else {
                    Country country = this.modelMapper.map(importCountry, Country.class);
                    this.countryRepository.save(country);

                    String message = String.format("Successfully imported country %s - %s", country.getCountryName(), country.getCurrency());
                    result.add(message);
                }
            } else {
                result.add("Invalid country");
            }
        }
        return String.join("/n", result);
    }
}
