package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartService;

import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PartsServiceImpl implements PartService {
    private static final Path path = Path.of("src", "main", "resources", "files", "json", "parts.json");

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
        return Files.readString(path);
    }

    @Override
    public String importParts() throws IOException {
        return null;
    }
}
