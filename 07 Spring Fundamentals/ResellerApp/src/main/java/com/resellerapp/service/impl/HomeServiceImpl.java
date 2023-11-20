package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.service.HomeService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    private final UserService userService;
    private final OfferService offerService;

    public HomeServiceImpl(UserService userService, OfferService offerService) {
        this.userService = userService;
        this.offerService = offerService;
    }

    @Override
    public void buyOffer(Long offerId, Long userId) {
        Offer offer = this.offerService.findById(offerId);
        User user = this.userService.findById(userId);

        this.userService.addToBoughtOffers(userId, offer);
        this.offerService.addBuyerToOffer(offerId, user);


        //TODO: to fix seller is null before click on Buy

        //  User userBuyer = userService.findUserById(userBuyerId).orElse(null);
        //        User seller = userRepo.findUserByOffers_Id(offerId).orElse(null);
        //        Offer offerItem = seller.getOffers().stream().filter(e -> e.getId().equals(offerId)).findFirst().orElse(null);
        //        boolean remove = seller.getOffers().remove(offerItem);
        //
        //        boolean add = userBuyer.getBoughtItems().add(offerItem);
        //
        //        userRepo.save(seller);
        //        userRepo.save(userBuyer);
        //
        //
        ////        removeOfferById(offerId);
        ////        offerRepo.save(offerItem);

    }
}
