package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.importXML.PictureImportDTO;
import softuni.exam.domain.dtos.importXML.RootPicturesImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

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
public class PictureServiceImpl implements PictureService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "pictures.xml");
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository,
                              ModelMapper modelMapper,
                              Validator validator) throws JAXBException {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(RootPicturesImportDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public String importPictures() throws FileNotFoundException, JAXBException {

        RootPicturesImportDTO root = (RootPicturesImportDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (PictureImportDTO importPicture : root.getPictures()) {
            Set<ConstraintViolation<PictureImportDTO>> validationErrors =
                    this.validator.validate(importPicture);

            if (validationErrors.isEmpty()) {
                Optional<Picture> optionalPicture
                        = this.pictureRepository.findByUrl(importPicture.getUrl());
                if (optionalPicture.isEmpty()) {
                    Picture picture = this.modelMapper.map(importPicture, Picture.class);

                    this.pictureRepository.save(picture);

                    String message = String.format("Successfully imported picture - %s", picture.getUrl());
                    result.add(message);
                } else {
                    result.add("Invalid picture");
                }
            } else {
                result.add("Invalid picture");
            }
        }

        return String.join("\n", result);
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files.readString(path);
    }

}
