package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.importXML.RootTeamsImportDTO;
import softuni.exam.domain.dtos.importXML.TeamImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.TeamService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "teams.xml");

    private final TeamRepository teamRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;

    public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(RootTeamsImportDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public String importTeams() throws FileNotFoundException, JAXBException {
        RootTeamsImportDTO root = (RootTeamsImportDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (TeamImportDTO importTeam : root.getTeams()) {
            Set<ConstraintViolation<TeamImportDTO>> validationErrors = this.validator.validate(importTeam);
            if (validationErrors.isEmpty()) {
                Optional<Team> optionalTeam = this.teamRepository.findByName(importTeam.getName());
                if (optionalTeam.isEmpty()) {

                    Team team = this.modelMapper.map(importTeam, Team.class);
                    Optional<Picture> picture = this.pictureRepository.findByUrl(importTeam.getPicture().getUrl());
                    if (picture.isPresent()) {
                        team.setPicture(picture.get());
                        this.teamRepository.save(team);

                        String message = String.format("Successfully added team - %s", team.getName());
                        result.add(message);
                    } else {
                        result.add("Invalid team");
                    }

                } else {
                    result.add("Invalid team");
                }
            } else {
                result.add("Invalid team");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public boolean areImported() {
      return   this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
       return Files.readString(path);
    }
}
