package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaxxer.hikari.HikariConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTownDTO;
import softuni.exam.models.entity.Town;
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

    private final TownRepository townRepository;

    private final Path path =
            Path.of("src", "main", "resources", "files", "json", "towns.json");
    private Gson gson;
    private ModelMapper modelMapper;
    private Validator validator;


    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();

        this.validator = Validation.
                buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        ImportTownDTO[] importTownDTOS = this.gson.fromJson(json, ImportTownDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportTownDTO importTownDTO: importTownDTOS) {
            // if valid?
            Set<ConstraintViolation<ImportTownDTO>> validationErrors =
                    this.validator.validate(importTownDTO);

            if (validationErrors.isEmpty()) {
                Optional<Town> optionalTown =
                        this.townRepository
                                .findByTownName(importTownDTO.getTownName());
                if (optionalTown.isEmpty()) {
                    // insert
                    Town town = this.modelMapper.map(importTownDTO, Town.class);

                    this.townRepository.save(town);

                    String msg = String.format("Successfully imported Town %s - %d",
                            town.getTownName(), town.getPopulation());

                    result.add(msg);
                } else {

                    result.add("invalid Town");
                }

            } else {
                result.add("Invalid Town");
            }


        }
        return String.join("\n", result);

    }
}
