package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importAgents.ImportAgentsDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;

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
public class AgentServiceImpl implements AgentService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "agents.json");
    private final AgentRepository agentRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.gson = new GsonBuilder().create();
    }


    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();
        ImportAgentsDTO[] agents = this.gson.fromJson(json, ImportAgentsDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportAgentsDTO importAgent : agents) {

            Set<ConstraintViolation<ImportAgentsDTO>> validationErrors
                    = this.validator.validate(importAgent);

            if (validationErrors.isEmpty()) {
                Optional<Agent> optionalAgent = this.agentRepository.findByFirstName(importAgent.getFirstName());
                if (optionalAgent.isPresent()) {
                    result.add("Invalid town");
                } else {
                    Agent agent = this.modelMapper.map(importAgent, Agent.class);
                    this.agentRepository.save(agent);

                    String message = String
                            .format("Successfully imported agent %s %s", agent.getFirstName(), agent.getLastName());
                    result.add(message);
                }

            } else {
                result.add("Invalid town");
            }
        }


        return String.join("\n", result);
    }
}
