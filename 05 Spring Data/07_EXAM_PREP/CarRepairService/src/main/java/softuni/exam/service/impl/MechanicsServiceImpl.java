package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MechanicsServiceImpl implements MechanicService {
    private  static final Path path = Path.of("src", "main", "resources", "files", "json", "mechanics.json");

    private final MechanicsRepository mechanicsRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository,
                                Gson gson,
                                ModelMapper modelMapper,
                                Validator validator) {

        this.mechanicsRepository = mechanicsRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importMechanics() throws IOException {
        return null;
    }
}
