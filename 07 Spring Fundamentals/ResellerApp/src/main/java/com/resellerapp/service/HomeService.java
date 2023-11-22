package com.resellerapp.service;

public interface HomeService {
    void buyOffer(Long offerId, Long userId);

    void removeOffer(Long offerId, Long userId);
}
