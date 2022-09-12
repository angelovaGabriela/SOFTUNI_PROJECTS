package com.example.xml_processing_exercise.productshop.services;


import com.example.xml_processing_exercise.productshop.entities.products.ExportProductsInRangeDTO;

public interface ProductsService {

    ExportProductsInRangeDTO getInRange(float from, float to);
}
