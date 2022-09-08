package com.example.jsonexe.productshop.services;

import com.example.jsonexe.productshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();
}
