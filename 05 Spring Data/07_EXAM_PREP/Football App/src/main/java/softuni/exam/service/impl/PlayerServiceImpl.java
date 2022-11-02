package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PlayerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerServiceImpl implements PlayerService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "players.json");

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             PictureRepository pictureRepository,
                             ModelMapper modelMapper,
                             Validator validator, Gson gson) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public String importPlayers() throws IOException {
        String json = this.readPlayersJsonFile();
        PlayersImportDTO[] players = this.gson.fromJson(json, PlayersImportDTO[].class);

        List<String> result = new ArrayList<>();
        for (PlayersImportDTO importPlayer : players) {
            Set<ConstraintViolation<PlayersImportDTO>> validationErrors = this.validator.validate(importPlayer);

            if (validationErrors.isEmpty()) {
                Optional<Player> optionalPlayer = this.playerRepository.findByFirstNameAndLastName(importPlayer.getFirstName(), importPlayer.getLastName());
                if (optionalPlayer.isEmpty()) {
                    Player player = this.modelMapper.map(importPlayer, Player.class);
                    Optional<Picture> picture = this.pictureRepository.findByUrl(player.getPicture().getUrl());
                    Optional<Team> team = this.teamRepository.findByNameAndPictureUrl(player.getTeam().getName(), player.getTeam().getPicture().getUrl());

                    if (picture.isPresent() && team.isPresent()) {
                        player.setPicture(picture.get());
                        player.setTeam(team.get());
                        this.playerRepository.save(player);

                        String message = String.format("Successfully imported player %s %s", player.getFirstName(), player.getLastName());
                        result.add(message);
                    } else {
                        result.add("Invalid player");
                    }

                } else {
                    result.add("Invalid player");
                }
            } else {
                result.add("Invalid player");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        BigDecimal greaterThan = BigDecimal.valueOf(100000);

        Set<Player> players = this.playerRepository.findBySalaryGreaterThanOrderBySalaryDesc(greaterThan);

        return players.stream().map(Player::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String exportPlayersInATeam() {
        String teamName = "North Hub";
        Set<Player> players = this.playerRepository.findPlayerByTeamNameOrderByIdAsc(teamName);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Team: %s%n", teamName));
        players.forEach(player -> builder.append(String.format("Player name: %s %s - %s%nNumber: %d%n",
                player.getFirstName(), player.getLastName(), player.getPosition().toString(), player.getNumber())));

        return builder.toString();
    }
}
