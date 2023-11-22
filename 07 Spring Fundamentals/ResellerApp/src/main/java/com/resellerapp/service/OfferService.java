package com.resellerapp.service;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.model.view.OfferViewModel;

import java.util.List;

public interface OfferService {
    void addOffer(OfferServiceModel offerServiceModel);

    List<OfferViewModel> findAllOtherOffers();

    List<OfferViewModel> findAllMyOffers();

    Offer findById(Long offerId);


}
