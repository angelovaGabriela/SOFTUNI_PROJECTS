package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.view.OfferViewModel;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.HomeService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.BinaryOperator;

@Service
public class HomeServiceImpl implements HomeService {

    private final ModelMapper modelMapper;

    private final UserService userService;
    private final OfferService offerService;

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    public HomeServiceImpl(ModelMapper modelMapper, UserService userService, OfferService offerService, UserRepository userRepository, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.offerService = offerService;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void buyOffer(Long offerId, Long userId) {
        // search the offer
        Offer offer = this.offerService.findById(offerId);
        // search the buyer
        User buyer = this.userService.findById(userId);
        // search the seller
        User seller = this.userRepository.findUserByOffersId(offerId);

        //get the offer for salle
        Offer offerItem = seller.getOffers().stream().filter(item -> item.getId().equals(offerId)).findFirst().orElse(null);

        boolean remove = seller.getOffers().remove(offerItem);
        boolean add = buyer.getBoughtOffers().add(offerItem);

        userRepository.save(seller);
        userRepository.save(buyer);


        if (remove && add) {
            offer.setBuyer(buyer);
            this.offerRepository.save(offer);
        }
        //removeOfferById(offerId);
        ////        offerRepo.save(offerItem);

    }

    @Transactional
    @Override
    public void removeOffer(Long offerId, Long userId) {

    //TODO: to remove offer from "My offers"


//        User user = this.userService.findById(userId);
//        this.userService.saveUser(user);
//        this.offerRepository.delete(modelMapper.map(offer, Offer.class));
    }
}
