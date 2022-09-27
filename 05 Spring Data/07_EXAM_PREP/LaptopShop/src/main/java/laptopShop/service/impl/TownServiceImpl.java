package laptopShop.service.impl;

import laptopShop.model.Town;
import laptopShop.model.dtos.importTowns.ImportTownsDTO;
import laptopShop.model.dtos.importTowns.TownImportDTO;
import laptopShop.repository.TownRepository;
import laptopShop.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TownServiceImpl implements TownService {
    private static final Path path = Path.of("src","main", "resources", "files", "xml", "towns.xml");

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator  validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) throws JAXBException {
        this.townRepository = townRepository;

        JAXBContext context = JAXBContext.newInstance(ImportTownsDTO.class);
        this.unmarshaller = context.createUnmarshaller();


        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTowns() throws JAXBException, IOException {

        ImportTownsDTO importTowns =
                (ImportTownsDTO) unmarshaller
                         .unmarshal(new FileReader(path.toAbsolutePath().toString()));
        List<String> result = new ArrayList<>();
        // Validation check
        for (TownImportDTO townImportDTO : importTowns.getTowns()) {

            Set<ConstraintViolation<TownImportDTO>> validationErrors
                    = this.validator.validate(townImportDTO);


            if (validationErrors.isEmpty()) {
                Optional<Town> optionalTown =
                        this.townRepository.findByName(townImportDTO.getName());

                // if the town exist in the DB
                if (optionalTown.isEmpty()) {
                    Town town = this.modelMapper.map(townImportDTO, Town.class);
                    this.townRepository.save(town);

                    String message = String.format("Successfully imported Town %s", town.getName());
                    result.add(message);
                }
                else {
                    result.add("Invalid Town");
                }
            }
            else {
                result.add("Invalid Town");
            }

        }

        return String.join("\n", result);
    }
}
