package com.example.jsonexe.productshop.services;

import com.example.jsonexe.productshop.entities.categoties.Category;
import com.example.jsonexe.productshop.entities.categoties.CategoryStats;
import com.example.jsonexe.productshop.entities.products.ProductWithoutBuyerDTO;
import com.example.jsonexe.productshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);

        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(rangeStart, rangeEnd);

    }

    @Override
    public List<CategoryStats> getCategoryStatistics() {
        return this.productRepository.getCategoryStats();

    }


}
