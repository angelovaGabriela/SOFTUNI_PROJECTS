package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.offer.AddOfferDTO;
import bg.softuni.mobilele.model.dto.offer.CardListingOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository,
                        OfferMapper offerMapper,
                        UserRepository userRepository,
                        ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails){
        OfferEntity newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = (userRepository.findByEmail(userDetails.getUsername()).orElseThrow());


        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);


    }

    public List<CardListingOfferDTO> findOfferByOfferName(String query) {

        return this.offerRepository
                .findAllByModel_NameContains(query)
                .stream()
                .map(offerMapper::offerEntityToCardListingOfferDTO)
                .collect(Collectors.toList());
    }



}
