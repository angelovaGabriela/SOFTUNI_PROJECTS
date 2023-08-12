package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportMechanicsDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MechanicsServiceImpl implements MechanicService {

    private static final String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";

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
        return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        String json = this.readMechanicsFromFile();
        ImportMechanicsDTO[] importMechanics = this.gson.fromJson(json, ImportMechanicsDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportMechanicsDTO importMechanic : importMechanics) {
            Set<ConstraintViolation<ImportMechanicsDTO>> validationErrors =
                    this.validator.validate(importMechanic);

            if (validationErrors.isEmpty()) {
             Optional<Mechanic> optionalMechanic = this.mechanicsRepository.findByEmail(importMechanic.getEmail());

             if (optionalMechanic.isPresent()) {
                 result.add("Invalid mechanic");
             } else {
                 Mechanic mechanic = this.modelMapper.map(importMechanic, Mechanic.class);
                 this.mechanicsRepository.save(mechanic);

                 String message = String.format("Successfully imported mechanic %s %s", mechanic.getFirstName(), mechanic.getLastName());
                 result.add(message);
             }
            } else {
                result.add("Invalid mechanic");
            }
        }

        return String.join("\n", result);
    }
}
