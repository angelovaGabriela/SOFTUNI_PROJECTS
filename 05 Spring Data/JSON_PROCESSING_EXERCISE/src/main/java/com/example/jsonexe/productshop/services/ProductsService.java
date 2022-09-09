package com.example.jsonexe.productshop.services;

import com.example.jsonexe.productshop.entities.categoties.CategoryStats;
import com.example.jsonexe.productshop.entities.products.ProductWithoutBuyerDTO;


import java.util.List;

public interface ProductsService {

    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to);

    List<CategoryStats> getCategoryStatistics();
}
