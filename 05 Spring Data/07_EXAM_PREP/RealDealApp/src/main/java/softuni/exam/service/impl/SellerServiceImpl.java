package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importXML.ImportSellerDTO;
import softuni.exam.models.dtos.importXML.RootSellerImportDTO;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SellerServiceImpl implements SellerService {

    private final static Path path = Path.of("src","main", "resources", "files", "xml", "sellers.xml");

    private final SellerRepository sellerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    private final Unmarshaller unmarshaller;

    public SellerServiceImpl(SellerRepository sellerRepository, Validator validator, ModelMapper modelMapper) throws JAXBException {
        this.sellerRepository = sellerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(RootSellerImportDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        RootSellerImportDTO root = (RootSellerImportDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (ImportSellerDTO sellerImport : root.getSellers()) {
            Set<ConstraintViolation<ImportSellerDTO>> validationErrors = this.validator.validate(sellerImport);

            if (validationErrors.isEmpty()) {
                Optional<Seller> optionalSeller = this.sellerRepository.findByEmail(sellerImport.getEmail());

                if (optionalSeller.isEmpty()) {
                    Seller seller = this.modelMapper.map(sellerImport, Seller.class);
                    this.sellerRepository.save(seller);

                    String message = String.format("Successfully imported seller %s - %s", seller.getLastName(), seller.getEmail());
                    result.add(message);
                } else {
                    result.add("Invalid seller");
                }
            } else {
                result.add("Invalid seller");
            }
        }

        return String.join("\n", result);

        //TODO: to run & import sellers
    }
}
