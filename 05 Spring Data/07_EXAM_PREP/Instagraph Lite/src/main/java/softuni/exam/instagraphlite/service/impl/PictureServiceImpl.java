package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.dtos.importPictures.PictureImportDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final static Path path = Path.of("src", "main", "resources", "files", "pictures.json");

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readFromFileContent();
        PictureImportDTO[] pictures = this.gson.fromJson(json, PictureImportDTO[].class);

        List<String> result = new ArrayList<>();
        for (PictureImportDTO pictureImport : pictures) {
            Set<ConstraintViolation<PictureImportDTO>> validationErrors
                    = this.validator.validate(pictureImport);

            if (validationErrors.isEmpty()) {
                Optional<Picture> optionalPicture = this.pictureRepository.findByPath(pictureImport.getPath());
                if (optionalPicture.isPresent()) {
                    result.add("Invalid picture");
                } else {
                    Picture picture = this.modelMapper.map(pictureImport, Picture.class);
                    this.pictureRepository.save(picture);

                    String message = String.format("Successfully imported Picture, with size %.2f", picture.getSize());
                    result.add(message);
                }
            } else {
                result.add("Invalid picture");
            }
        }


        return String.join("\n", result);
    }

    @Override
    public String exportPictures() {

        List<Picture> pictures = this.pictureRepository.findBySizeGreaterThanOrderBySizeAsc(30000);



        return pictures.stream().map(Picture::toString).collect(Collectors.joining("\n"));
    }
}
