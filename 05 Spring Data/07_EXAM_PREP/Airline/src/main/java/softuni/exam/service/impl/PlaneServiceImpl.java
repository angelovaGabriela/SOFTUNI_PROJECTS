package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importXML.plane.PlaneImportDTO;
import softuni.exam.models.dtos.importXML.plane.RootImportPlaneDTO;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;

import javax.validation.ConstraintViolation;
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
public class PlaneServiceImpl implements PlaneService {
    private final static Path path = Path.of("src","main", "resources", "files", "xml", "planes.xml");

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository,
                            ModelMapper modelMapper,
                            Validator validator) throws JAXBException {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        JAXBContext context = JAXBContext.newInstance(RootImportPlaneDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPlanes() throws FileNotFoundException, JAXBException {
        RootImportPlaneDTO planes = (RootImportPlaneDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (PlaneImportDTO importPlane : planes.getPlanes()) {

            Set<ConstraintViolation<PlaneImportDTO>> validationErrors
                    = this.validator.validate(importPlane);

            if (validationErrors.isEmpty()) {
                Optional<Plane> optionalPlane
                        = this.planeRepository
                        .findByRegisterNumber(importPlane.getRegisterNumber());
                if (optionalPlane.isPresent()) {
                    result.add("Invalid Plane");
                } else {

                    Plane plane = this.modelMapper.map(importPlane, Plane.class);
                    this.planeRepository.save(plane);

                    String message = String.format("Successfully imported Plane %s", plane.getRegisterNumber());
                    result.add(message);
                }
            } else {
                result.add("Invalid Plane");
            }
        }
        return String.join("\n", result);
    }
}
