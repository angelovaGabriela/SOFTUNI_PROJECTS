package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importJSON.TownImportDTO;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

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
public class TownServiceImpl implements TownService {
    private final static Path path = Path.of("src","main", "resources", "files", "json", "towns.json");

    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           Gson gson,
                           Validator validator,
                           ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
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

        for (TownImportDTO townImport: towns) {
            Set<ConstraintViolation<TownImportDTO>> validationErrors
                    = this.validator.validate(townImport);
            if (validationErrors.isEmpty()) {
                Optional<Town> optionalTown
                        = this.townRepository.findByName(townImport.getName());

                if (optionalTown.isPresent()) {
                    result.add("Invalid Town");
                } else {
                    Town town = this.modelMapper.map(townImport, Town.class);
                    this.townRepository.save(town);

                    String message = String.format("Successfully imported Town %s - %d", town.getName(), town.getPopulation());
                    result.add(message);
                }
            } else {
                result.add("Invalid Town");
            }
        }
        return String.join("\n", result);
    }
}
