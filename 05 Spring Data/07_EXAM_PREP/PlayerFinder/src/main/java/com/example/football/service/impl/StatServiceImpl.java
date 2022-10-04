package com.example.football.service.impl;

import com.example.football.models.dto.importStats.ImportStatsDTO;
import com.example.football.models.dto.importStats.StatImportDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StatServiceImpl implements StatService {

    private static final Path path = Path.of("src", "main", "resources", "files", "xml", "stats.xml");
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        JAXBContext context = JAXBContext.newInstance(ImportStatsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {
        ImportStatsDTO importStatsDTO
                = (ImportStatsDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (StatImportDTO statImport : importStatsDTO.getStats()) {

            Set<ConstraintViolation<StatImportDTO>> validationErrors
                    = this.validator.validate(statImport);

            if(validationErrors.isEmpty()) {

                Optional<Stat> optionalStat = this.statRepository
                        .findByPassingAndShootingAndEndurance(statImport.getPassing(),
                                statImport.getShooting(), statImport.getEndurance());

                if (optionalStat.isPresent()){
                    result.add("Invalid Stat");
                } else {
                    Stat stat = this.modelMapper.map(statImport, Stat.class);
                    this.statRepository.save(stat);

                    String message = String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                            stat.getPassing(), stat.getPassing(), stat.getEndurance());

                    result.add(message);
                }
            } else {
                result.add("Invalid Stat");
            }
        }

        return String.join("\n", result);
    }
}
