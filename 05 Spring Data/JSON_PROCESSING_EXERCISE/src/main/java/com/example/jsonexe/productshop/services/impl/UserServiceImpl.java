package com.example.jsonexe.productshop.services.impl;

import com.example.jsonexe.productshop.entities.users.User;
import com.example.jsonexe.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.jsonexe.productshop.repositories.UserRepository;
import com.example.jsonexe.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUsersWithSoldProducts() {

        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

       return allWithSoldProducts
                .stream()
                .map(user -> this.modelMapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<User> getUsersWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOderByCount();

        all.get(0).getSellingItems().size();


        //TODO: Select only their first and last name, age and for each product - name and price.
        // Export the results to JSON.

        //TODO: // DONE:
        // Get all users who have at least 1 product sold.
        // Order them by the number of products sold (from highest to lowest),
        // then by last name (ascending).

        return null;

    }
}
