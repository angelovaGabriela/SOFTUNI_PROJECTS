package com.example.football.service.impl;

import com.example.football.models.dto.ImportPlayerDTO;
import com.example.football.models.dto.ImportPlayerRootDOTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final Path path =
            Path.of("skeleton","src", "main", "resources", "files", "xml", "players.xml");


    private final PlayerRepository playerRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    private TeamRepository teamRepository;
    private TownRepository townRepository;
    private StatRepository statRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, TownRepository townRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;


        JAXBContext context = JAXBContext.newInstance(ImportPlayerRootDOTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

        Converter<String, LocalDate> toLocalDate = s -> s.getSource() == null ? null :
                LocalDate.parse(s.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        modelMapper.addConverter(toLocalDate);

    }


    @Override
    public boolean areImported() {

        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {

        ImportPlayerRootDOTO playerDTOs = (ImportPlayerRootDOTO) this.unmarshaller.unmarshal(
                new FileReader(path.toAbsolutePath().toString())
        );

        return playerDTOs
                .getPlayers()
                .stream()
                .map(this::importPlayer)
                .collect(Collectors.joining("\n"));


    }

    private String importPlayer(ImportPlayerDTO importPlayerDTO) {

        Set<ConstraintViolation<ImportPlayerDTO>> errors = this.validator.validate(importPlayerDTO);

        if (!errors.isEmpty()) {
            return "Invalid Player";

        }

        Optional<Player> optionalPlayer = this.playerRepository.findByEmail(importPlayerDTO.getEmail());


        if (optionalPlayer.isPresent()) {
            return "Invalid Player";
        }


        Optional<Team> team = this.teamRepository.findByName(importPlayerDTO.getTeam().getName());
        Optional<Town> town = this.townRepository.findByName(importPlayerDTO.getTown().getName());
        Optional<Stat> stat = this.statRepository.findById(importPlayerDTO.getStat().getId());


        Player player = this.modelMapper.map(importPlayerDTO, Player.class);

        player.setTeam(team.get());
        player.setTown(town.get());
        player.setStat(stat.get());

        this.playerRepository.save(player);

        return "Successfully imported Player " + player.getFirstName() +
                " " + player.getLastName() + " - " + player.getPosition().toString();


    }


    @Override
    public String exportBestPlayers() {
        return null;
    }
}
