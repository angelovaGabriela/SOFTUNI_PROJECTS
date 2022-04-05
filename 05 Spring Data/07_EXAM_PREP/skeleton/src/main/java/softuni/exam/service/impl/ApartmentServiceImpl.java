package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "apartments.xml");


    private ApartmentRepository apartmentRepository;


   @Autowired

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        return null;
    }
}
