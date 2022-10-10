package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importForecasts.ForecastImportDTO;
import softuni.exam.models.dto.importForecasts.ImportForecastsDTO;
import softuni.exam.models.entity.Forecast;

import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
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

public class ForecastServiceImpl implements ForecastService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");

    private final ForecastRepository forecastRepository;

    private final ModelMapper modelMapper;

    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper) throws JAXBException {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(ImportForecastsDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();



    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ImportForecastsDTO unmarshal = (ImportForecastsDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (ForecastImportDTO importForecast : unmarshal.getForecasts()) {

            Set<ConstraintViolation<ForecastImportDTO>> validationErrors
                    = validator.validate(importForecast);
            if (validationErrors.isEmpty()){
      //forecasts for the same day of week of the city
                Optional<Forecast> optionalForecast
                        = this.forecastRepository
                        .findByDayOfWeekAndCityId(importForecast.getDayOfWeek(),
                                importForecast.getCity().getId());

                if (optionalForecast.isPresent()) {
                    result.add("Invalid forecast");
                } else {
                    Forecast forecast = this.modelMapper.map(importForecast, Forecast.class);
                    this.forecastRepository.save(forecast);

                    String message =
                            String.format("Successfully import forecast %s - %.2f",
                                    forecast.getDayOfWeek(), forecast.getMaxTemperature());
                    result.add(message);
                }
            } else {
                result.add("Invalid forecast");
            }

        }
        return String.join("\n", result);
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
