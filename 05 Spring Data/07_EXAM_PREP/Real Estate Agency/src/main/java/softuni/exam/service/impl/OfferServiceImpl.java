package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.importOffers.ImportOfferDTO;
import softuni.exam.models.dto.importOffers.RootImportOffersDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.transaction.Transactional;
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
public class OfferServiceImpl implements OfferService {
    private final static Path path = Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository,
                            AgentRepository agentRepository,
                            ApartmentRepository apartmentRepository,
                            ModelMapper modelMapper) throws JAXBException {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        JAXBContext context = JAXBContext.newInstance(RootImportOffersDTO.class);
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
        RootImportOffersDTO offers = (RootImportOffersDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (ImportOfferDTO importOffer : offers.getOffers()) {

            Set<ConstraintViolation<ImportOfferDTO>> validationErrors
                    = this.validator.validate(importOffer);

            if (validationErrors.isEmpty()) {
                //Optional<Offer> optionalOffer
                 //       = this.offerRepository.findOfferByAgentFirstName(importOffer.getAgent().getName());
                Optional<Agent> agent = this.agentRepository.findByFirstName(importOffer.getAgent().getName());
                if (agent.isEmpty()) {
                   result.add("Invalid offer");
                } else {
                    Offer offer = this.modelMapper.map(importOffer, Offer.class);

                    Optional<Apartment> apartment = this.apartmentRepository.findById(importOffer.getApartment().getId());

                    offer.setAgent(agent.get());
                    offer.setApartment(apartment.get());

                    this.offerRepository.save(offer);

                    String message = String.format("Successfully imported offer %.2f", offer.getPrice());
                    result.add(message);
                }
            } else {
                result.add("Invalid offer");
            }
        }


        return String.join("\n", result);
    }

    @Override
    public String exportOffers() {
        return null;
    }
}
