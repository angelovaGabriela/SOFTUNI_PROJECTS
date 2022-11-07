package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.DTO.AddOfferDTO;
import bg.softuni.mobilele.model.DTO.BrandDTO;
import bg.softuni.mobilele.model.ModelEntity;
import bg.softuni.mobilele.model.OfferEntity;
import bg.softuni.mobilele.model.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private OfferMapper offerMapper;
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private ModelRepository modelRepository;

    public OfferService(OfferRepository offerRepository,
                        OfferMapper offerMapper,
                        UserRepository userRepository,
                        CurrentUser currentUser,
                        ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelRepository = modelRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO){
        OfferEntity newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDTO);

        //TODO - current user should be logged in

        UserEntity seller = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId())
                .orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);

    }




}
