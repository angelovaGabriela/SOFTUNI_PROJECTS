package com.resellerapp.service;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.service.UserServiceModel;
import com.resellerapp.model.view.OfferViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    void saveUser(User seller);

    void addToBoughtOffers(Long userId, Offer offer);

    List<OfferViewModel> findBoughtOffers(Long userID);
}
