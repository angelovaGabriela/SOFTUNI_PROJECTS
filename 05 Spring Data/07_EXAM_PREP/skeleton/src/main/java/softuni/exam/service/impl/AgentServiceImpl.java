package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AgentServiceImpl implements AgentService {
    private final Path path =
            Path.of("src", "main", "resources", "files", "json", "agents.json");

    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    @Override
    public boolean areImported() {
      return  this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importAgents() throws IOException {
        return null;
    }
}
