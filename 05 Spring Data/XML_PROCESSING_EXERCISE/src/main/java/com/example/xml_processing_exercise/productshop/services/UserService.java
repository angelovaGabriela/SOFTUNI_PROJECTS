package com.example.xml_processing_exercise.productshop.services;

import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersDTO;
import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersWithCountsDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();

    ExportSellersWithCountsDTO findAllWithSoldProductsAndCounts();
}
