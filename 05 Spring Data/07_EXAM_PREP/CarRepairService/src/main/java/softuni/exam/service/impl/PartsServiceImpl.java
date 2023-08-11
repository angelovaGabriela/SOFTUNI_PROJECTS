package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.part.ImportPartsDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartService;

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
public class PartsServiceImpl implements PartService {
    private static final String path = "src/main/resources/files/json/parts.json";


    private final PartsRepository partsRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;


    private final Validator validator;

    @Autowired
    public PartsServiceImpl(PartsRepository partsRepository,
                            Gson gson,
                            ModelMapper modelMapper,
                            Validator validator) {
    this.partsRepository = partsRepository;

        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(path));
    }

    @Override
    public String importParts() throws IOException {

        String json = this.readPartsFileContent();
        ImportPartsDTO[] parts = this.gson.fromJson(json, ImportPartsDTO[].class);

        List<String> result = new ArrayList<>();
        for (ImportPartsDTO importPart : parts) {
            Set<ConstraintViolation<ImportPartsDTO>> validationErrors = this.validator.validate(importPart);
            if (validationErrors.isEmpty()) {
                Optional<Part> optionalPart = this.partsRepository.findByPartName(importPart.getPartName());

                if (optionalPart.isPresent()) {
                    result.add("Invalid part");
                } else {
                    Part part = this.modelMapper.map(importPart, Part.class);
                    this.partsRepository.save(part);

                    String message = String.format("Successfully imported part %s - %.2f", part.getPartName(), part.getPrice());
                    result.add(message);
                }
            } else {
                result.add("Invalid part");
            }
        }
        return String.join("\n", result);
    }
}
