package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ConditionService conditionService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;

    public OfferServiceImpl(OfferRepository offerRepository,
                            ConditionService conditionService, ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(offerServiceModel, Offer.class);
        User seller = this.userService.findById(currentUser.getId());


        offer.setCondition(this.conditionService.findByConditionName(offerServiceModel.getCondition()));

        //adding bidirectional relation because the offer needs to know her creator
        offer.setSeller(seller);
        this.offerRepository.save(offer);


        //adding the offer to the offers collection of the seller
        seller.offerAddedByMe(offer);
        this.userService.saveUser(seller);

    }

    @Override
    public List<OfferViewModel> findAllOtherOffers() {
        // filtering only the offers from other users
        return offerRepository.findAll()
                .stream().map(offer -> modelMapper.map(offer, OfferViewModel.class))
                .filter(offerViewModel -> !Objects.equals(offerViewModel.getSeller().getId(), currentUser.getId()))
                .filter(offerViewModel -> offerViewModel.getBuyer() == null).collect(Collectors.toList());

    }

    @Override
    public List<OfferViewModel> findAllMyOffers() {
        // filtering only the offers from other users && only offers with NO buyer

        return offerRepository.findAll()
                .stream().map(offer -> modelMapper.map(offer, OfferViewModel.class))
                .filter(offerViewModel -> Objects.equals(offerViewModel.getSeller().getId(), currentUser.getId()))
                .filter(offerViewModel -> offerViewModel.getBuyer() == null).collect(Collectors.toList());

    }

    @Override
    public Offer findById(Long offerId) {
        return this.offerRepository.findById(offerId).orElse(null);
    }



//    @Override
//    public void addBuyerToOffer(Long offerId, User user) {
//        Offer offer = this.findById(offerId);
//        offer.setBuyer(user);
//        this.offerRepository.save(offer);
//    }
}
