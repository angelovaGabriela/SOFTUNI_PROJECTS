package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importJSON.ImportPersonDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "people.json");

    private final PersonRepository personRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             CountryRepository countryRepository,
                             Gson gson, ModelMapper modelMapper,
                             Validator validator) {
        this.personRepository = personRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPeople() throws IOException, JAXBException {
        String json = this.readPeopleFromFile();
        ImportPersonDTO[] people = this.gson.fromJson(json, ImportPersonDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportPersonDTO personDTO : people) {

            Set<ConstraintViolation<ImportPersonDTO>> validationErrors = this.validator.validate(personDTO);
            if (validationErrors.isEmpty()) {

                Optional<Person> byEmailAndFirstName = this.personRepository.findByEmailAndFirstName(personDTO.getEmail(), personDTO.getFirstName());
                Optional<Person> byPhone = this.personRepository.findByPhone(personDTO.getPhone());

                if (byEmailAndFirstName.isPresent() || byPhone.isPresent()) {
                    result.add("Invalid person");
                } else {
                    Person person = this.modelMapper.map(personDTO, Person.class);

                    long countryId = Long.parseLong(personDTO.getCountry());
                    Optional<Country> country = this.countryRepository.findById(countryId);

                    person.setCountry(country.get());
                    this.personRepository.save(person);

                    result.add(String.format("Successfully added %s %s", person.getFirstName(), person.getLastName()));
                }
            } else {
                result.add("Invalid person");
            }
        }

        return String.join("\n", result);
    }
}
