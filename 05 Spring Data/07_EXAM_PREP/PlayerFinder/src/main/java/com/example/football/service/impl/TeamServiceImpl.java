package com.example.football.service.impl;

import com.example.football.models.dto.importTeams.ImportTeamsDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }


    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();

        ImportTeamsDTO[] teams = this.gson.fromJson(json, ImportTeamsDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportTeamsDTO importTeam : teams) {

            Set<ConstraintViolation<ImportTeamsDTO>> validationErrors
                    = this.validator.validate(importTeam);
            if (validationErrors.isEmpty()) {
                Optional<Team> optionalTeam = this.teamRepository.findByName(importTeam.getName());
                if (optionalTeam.isPresent()) {
                    result.add("Invalid Team");
                } else {
                    Team team = this.modelMapper.map(importTeam, Team.class);

                    Optional<Town> town = this.townRepository.findByName(importTeam.getTownName());
                    team.setTown(town.get());

                    this.teamRepository.save(team);

                    String message = String.format("Successfully imported Team %s - %d", team.getName(), team.getFanBase());
                    result.add(message);
                }
            } else {
                result.add("Invalid Team");
            }
        }

        return String.join("\n", result);
    }
}
