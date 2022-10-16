package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.entity.Town;
import softuni.exam.models.dto.importTowns.TownImportDTO;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

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
public class TownServiceImpl implements TownService {

    private final static Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        TownImportDTO[] towns = this.gson.fromJson(json, TownImportDTO[].class);
        List<String> result = new ArrayList<>();

        for (TownImportDTO townImport : towns) {

            Set<ConstraintViolation<TownImportDTO>> validationErrors
                    = this.validator.validate(townImport);

            if (validationErrors.isEmpty()) {
                Optional<Town> optionalTown = this.townRepository.findByTownName(townImport.getTownName());
                if (optionalTown.isPresent()) {
                    result.add("Invalid town");
                } else {
                    Town town = this.modelMapper.map(townImport, Town.class);
                    this.townRepository.save(town);
                    String message = String
                            .format("Successfully imported town %s - %d", town.getTownName(), town.getPopulation());
                    result.add(message);
                }
            } else {
                result.add("Invalid town");
            }
        }
        return String.join("\n", result);
    }
}
