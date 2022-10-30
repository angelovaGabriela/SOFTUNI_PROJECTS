package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importXML.ImportOfferDTO;
import softuni.exam.models.dtos.importXML.RootOffersImportDTO;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
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
public class OfferServiceImpl implements OfferService {
    private final static Path path = Path.of("src","main", "resources", "files", "xml", "offers.xml");

    private final OfferRepository offerRepository;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    private final Unmarshaller unmarshaller;

    public OfferServiceImpl(OfferRepository offerRepository,
                            CarRepository carRepository,
                            SellerRepository sellerRepository,
                            Validator validator,
                            ModelMapper modelMapper) throws JAXBException {
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(RootOffersImportDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        RootOffersImportDTO root = (RootOffersImportDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();
        for (ImportOfferDTO offerImport : root.getOffers()) {
            Set<ConstraintViolation<ImportOfferDTO>> validationErrors =
                    this.validator.validate(offerImport);

            if (validationErrors.isEmpty()) {
                Optional<Offer> optionalOffer =
                        this.offerRepository.findByDescriptionAndAddedOn(offerImport.getDescription(), offerImport.getAddedOn());

                if (optionalOffer.isEmpty()) {
                    Offer offer = this.modelMapper.map(offerImport, Offer.class);

                    Optional<Seller> seller = this.sellerRepository.findById(offer.getSeller().getId());
                    Optional<Car> car = this.carRepository.findById(offer.getCar().getId());

                    offer.setSeller(seller.get());
                    offer.setCar(car.get());

                    this.offerRepository.save(offer);

                    String message = String.format("Successfully imported offer %s - %s", offer.getAddedOn().toString(), offer.isHasGoldStatus());
                    result.add(message);

                } else {
                    result.add("Invalid offer");
                }
            } else {
                result.add("Invalid offer");
            }
        }

        return String.join("\n", result);
    }
}
