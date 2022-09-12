package com.example.xml_processing_exercise.productshop.services;

import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();
}
