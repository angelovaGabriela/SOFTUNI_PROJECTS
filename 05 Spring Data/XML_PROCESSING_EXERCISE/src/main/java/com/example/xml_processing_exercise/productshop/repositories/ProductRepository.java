package com.example.xml_processing_exercise.productshop.repositories;

import com.example.xml_processing_exercise.productshop.entities.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc
     (BigDecimal rangeFrom, BigDecimal rangeTo);

}
