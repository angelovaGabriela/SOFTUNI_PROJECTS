package com.example.football.service.impl;

import com.example.football.models.dto.importPlayers.ImportPlayersDTO;
import com.example.football.models.dto.importPlayers.PlayerImportDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Path path = Path.of("src", "main", "resources", "files", "xml", "players.xml");

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;

    private final Validator validator;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             TownRepository townRepository,
                             StatRepository statRepository,
                             ModelMapper modelMapper) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        JAXBContext context = JAXBContext.newInstance(ImportPlayersDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.modelMapper = modelMapper;
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
        ImportPlayersDTO playersDTO
                = (ImportPlayersDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (PlayerImportDTO playerImport : playersDTO.getPlayers()) {

            Set<ConstraintViolation<PlayerImportDTO>> validationErrors
                    = this.validator.validate(playerImport);

            if (validationErrors.isEmpty()) {
                Optional<Player> optionalPlayer
                        = this.playerRepository.findByEmail(playerImport.getEmail());

                if (optionalPlayer.isPresent()) {
                    result.add("Invalid Player");
                } else {

                    Optional<Town> town = this.townRepository.findByName(playerImport.getTown().getName());
                    Optional<Team> team = this.teamRepository.findByName(playerImport.getTeam().getName());
                    Optional<Stat> stat = this.statRepository.findById(playerImport.getStat().getId());

                    Player player = this.modelMapper.map(playerImport, Player.class);

                    player.setTown(town.get());
                    player.setTeam(team.get());
                    player.setStat(stat.get());

                    this.playerRepository.save(player);

                    String message = String.format("Successfully imported Player %s - %s - %s",
                            player.getFirstName(), player.getLastName(), player.getPosition().toString());

                    result.add(message);
                }
            } else {
                result.add("Invalid Player");
            }
        }

        return String.join("\n", result);
    }

    @Override
    public String exportBestPlayers() {

        LocalDate before = LocalDate.of(2003, 1, 1);
        LocalDate after = LocalDate.of(1995, 1, 1);

        List<Player> players = this.playerRepository.findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after, before);

       return players.stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }
}
