package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importApartments.ImportApartmentDTO;
import softuni.exam.models.dto.importApartments.RootImportApartmentsDTO;
import softuni.exam.models.entity.Apartment;

import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;

import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

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
public class ApartmentServiceImpl implements ApartmentService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");

    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;


        this.validator  = Validation.buildDefaultValidatorFactory().getValidator();
        this.modelMapper = new ModelMapper();

        JAXBContext context = JAXBContext.newInstance(RootImportApartmentsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        RootImportApartmentsDTO apartments = (RootImportApartmentsDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (ImportApartmentDTO importApartment : apartments.getApartments()) {

            Set<ConstraintViolation<ImportApartmentDTO>> validationErrors =
                    this.validator.validate(importApartment);
            if (validationErrors.isEmpty()) {


                Optional<Apartment> optionalApartment = this.apartmentRepository
                        .findApartmentByTownTownNameAndArea
                                (importApartment.getTown(), importApartment.getArea());
                if (optionalApartment.isPresent()) {
                    result.add("Invalid apartment");
                } else {
                    Apartment apartment = this.modelMapper.map(importApartment, Apartment.class);

                    Optional<Town> town = this.townRepository.findByTownName(importApartment.getTown());
                    apartment.setTown(town.get());

                    this.apartmentRepository.save(apartment);
                    String message = String.format("Successfully imported apartment %s - %.2f", apartment.getApartmentType().toString(), apartment.getArea());

                    result.add(message);

                }
            } else {
                result.add("Invalid apartment");
            }
        }
        return String.join("\n", result);
    }
}
