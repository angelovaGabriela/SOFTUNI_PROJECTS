package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.service.OfferServiceModel;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.service.ConditionService;
import com.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ConditionService conditionService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository,
                            ConditionService conditionService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.conditionService = conditionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(offerServiceModel, Offer.class);
        offer.setCondition(this.conditionService.findByConditionName(offerServiceModel.getCondition()));
        this.offerRepository.save(offer);
    }
}
