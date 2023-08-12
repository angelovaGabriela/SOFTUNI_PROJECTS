package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDTO;
import softuni.exam.models.dto.StarImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;

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
public class StarServiceImpl implements StarService {
    private static final String STAR_FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public StarServiceImpl(StarRepository starRepository,
                           ConstellationRepository constellationRepository,
                           ModelMapper modelMapper,
                           Gson gson,
                           Validator validator) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(STAR_FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
        String json = this.readStarsFileContent();
        StarImportDTO[] starImportDTOS = this.gson.fromJson(json, StarImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (StarImportDTO importDTO : starImportDTOS) {
            Set<ConstraintViolation<StarImportDTO>> validationErrors =
                    this.validator.validate(importDTO);

            if (validationErrors.isEmpty()) {
                Optional<Star> optionalStar = this.starRepository.findByName(importDTO.getName());

                if (optionalStar.isPresent()) {
                    result.add("Invalid star");
                } else {
                    Star star = this.modelMapper.map(importDTO, Star.class);
                    Optional<Constellation> constellation = this.constellationRepository.findById(importDTO.getConstellation());

                    if (constellation.isEmpty()) {
                        result.add("Invalid star");
                        continue;
                    }

                    star.setConstellation(constellation.get());
                    this.starRepository.save(star);

                    String message = String.format("Successfully imported star %s - %.2f light years", importDTO.getName(), importDTO.getLightYears());
                    result.add(message);
                }
            } else {
                result.add("Invalid star");
            }
        }

        return String.join("\n", result);
    }

    @Override
    public String exportStars() {
        return null;
    }
}
