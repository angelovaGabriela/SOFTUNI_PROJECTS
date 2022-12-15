package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.importJSON.EmployeeCardDTO;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final static Path path = Path.of("src","main", "resources", "files", "employee-cards.json");

    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository,
                                   Gson gson, ModelMapper modelMapper,
                                   Validator validator) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
       String json = this.readEmployeeCardsJsonFile();
        EmployeeCardDTO[] cards = this.gson.fromJson(json, EmployeeCardDTO[].class);

        List<String> result = new ArrayList<>();

        for (EmployeeCardDTO card : cards) {
            Set<ConstraintViolation<EmployeeCardDTO>> validationErrors
                    = validator.validate(card);
            if (validationErrors.isEmpty()) {
                Optional<EmployeeCard> optionalCard = this.employeeCardRepository.findByNumber(card.getNumber());
                if (optionalCard.isPresent()) {
                    result.add(Constants.INCORRECT_DATA_MESSAGE);
                } else {
                    EmployeeCard cardToSave = this.modelMapper.map(card, EmployeeCard.class);
                    this.employeeCardRepository.save(cardToSave);
                    result.add(String.format("Successfully added employee card with number %s", cardToSave.getNumber()));
                }
            } else {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            }
        }


        return String.join("\n", result);
    }
}
