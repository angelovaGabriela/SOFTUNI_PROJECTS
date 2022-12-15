package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.importJSON.ImportTownDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final static Path path = Path.of("src","main", "resources", "files", "towns.json");

    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           Gson gson,
                           ModelMapper modelMapper,
                           Validator validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        String json = this.readTownsJsonFile();
        ImportTownDTO[] towns = this.gson.fromJson(json, ImportTownDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportTownDTO town : towns) {
            Set<ConstraintViolation<ImportTownDTO>> validationErrors = this.validator.validate(town);

            if (validationErrors.isEmpty()) {
                Optional<Town> optionalTown = this.townRepository.findByName(town.getName());

                if (optionalTown.isPresent()) {
                    result.add(Constants.INCORRECT_DATA_MESSAGE);
                } else {
                    Town townToSave = this.modelMapper.map(town, Town.class);
                    this.townRepository.save(townToSave);

                    result.add(String.format("Successfully imported town %s", townToSave.getName()));
                }
            } else {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            }
        }
        return String.join("\n", result);
    }
}
