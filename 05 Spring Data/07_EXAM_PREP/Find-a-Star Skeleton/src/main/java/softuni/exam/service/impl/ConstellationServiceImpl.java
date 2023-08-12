package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;

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
public class ConstellationServiceImpl implements ConstellationService {
    private static final String CONSTELLATION_FILE_PATH = "src/main/resources/files/json/constellations.json";

    private final ConstellationRepository constellationRepository;
   private final Validator validator;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Validator validator, ModelMapper modelMapper, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.validator = validator;

        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(CONSTELLATION_FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {

        String json = this.readConstellationsFromFile();
        ConstellationImportDTO[] constellationImportDTOS = this.gson.fromJson(json, ConstellationImportDTO[].class);


        List<String> result = new ArrayList<>();
        for (ConstellationImportDTO importDTO : constellationImportDTOS) {
            Set<ConstraintViolation<ConstellationImportDTO>> validationErrors =
                    this.validator.validate(importDTO);

            if (validationErrors.isEmpty()) {
                Optional<Constellation> optionalConstellation = this.constellationRepository.findByName(importDTO.getName());

                if (optionalConstellation.isPresent()) {
                    result.add("Invalid constellation");
                } else {
                    Constellation constellation = this.modelMapper.map(importDTO, Constellation.class);
                    this.constellationRepository.save(constellation);

                    String message = String.format("Successfully imported constellation %s - %s", constellation.getName(), constellation.getDescription());
                    result.add(message);
                }
            } else {
                result.add("Invalid constellation");
            }
        }

        return String.join("\n", result);
    }


}
