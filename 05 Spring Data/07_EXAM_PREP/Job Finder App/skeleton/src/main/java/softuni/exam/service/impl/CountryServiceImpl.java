package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importJSON.ImportCountryDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

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
public class CountryServiceImpl implements CountryService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "countries.json");
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;


    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository,
                              ModelMapper modelMapper,
                              Gson gson, Validator validator) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCountries() throws IOException {
        String json = this.readCountriesFileContent();
        ImportCountryDTO[] countries = this.gson.fromJson(json, ImportCountryDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportCountryDTO countryDTO : countries) {

            Set<ConstraintViolation<ImportCountryDTO>> validationErrors = this.validator.validate(countryDTO);
            if (validationErrors.isEmpty()) {
                Optional<Country> optionalCountry = this.countryRepository.findByName(countryDTO.getName());
                if (optionalCountry.isPresent()) {
                    result.add("Invalid country");

                } else {
                    Country country = this.modelMapper.map(countryDTO, Country.class);
                    this.countryRepository.save(country);

                    result.add(String.format("Successfully added Country %s - %s", country.getName(), country.getCode()));
                }
            } else {

                result.add("Invalid country");
            }
        }
        return String.join("\n", result);
    }
}
