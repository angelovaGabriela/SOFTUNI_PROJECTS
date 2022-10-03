package com.example.football.service.impl;

import com.example.football.models.dto.importTowns.ImportTownsDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

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
        //Step I --> read the json file & extract to DTO
        String json = this.readTownsFileContent();
        ImportTownsDTO[] towns = this.gson.fromJson(json, ImportTownsDTO[].class);

        //Step II --> stream the towns

        List<String> result = new ArrayList<>();
        for (ImportTownsDTO importTown: towns) {

            //Step III --> validate the data

            Set<ConstraintViolation<ImportTownsDTO>> validationErrors = this.validator.validate(importTown);

            if(validationErrors.isEmpty()) {
                //STEP IV --> insert to the repository & return message
                // search the object in the repository
                Optional<Town> optionalTown = this.townRepository.findByName(importTown.getName());
                if (optionalTown.isPresent()) { // return message
                    result.add("Invalid Town");
                    } else {  // add to the repository
                    // map DTO to entity
                    Town town = this.modelMapper.map(importTown, Town.class);
                    // save to the repository
                    this.townRepository.save(town);

                    // message
                    String message = String.format("Successfully imported Town %s - %d", town.getName(), town.getPopulation());
                    result.add(message);
                }

            } else {
                //return message
                result.add("Invalid Town");
            }
        }
        // join everything on a new line
        return String.join("\n", result);
    }
}
