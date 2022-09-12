package com.example.xml_processing_exercise.productshop.services.impl;

import com.example.xml_processing_exercise.productshop.entities.products.ProductWithAttributesDTO;
import com.example.xml_processing_exercise.productshop.entities.products.Products;
import com.example.xml_processing_exercise.productshop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_processing_exercise.productshop.entities.users.User;
import com.example.xml_processing_exercise.productshop.repositories.ProductRepository;
import com.example.xml_processing_exercise.productshop.services.ProductsService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final TypeMap<Products, ProductWithAttributesDTO> productToDTOMapping;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();

        // transforming User to String
        // getting name information
        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Products, ProductWithAttributesDTO> typeMap =
                this.modelMapper.createTypeMap(Products.class, ProductWithAttributesDTO.class);

      this.productToDTOMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                        .map(Products ::getSeller, ProductWithAttributesDTO::setSeller));
    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf(from);
        BigDecimal rangeTo = BigDecimal.valueOf(to);

        List<Products> products =  this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(rangeFrom, rangeTo);

        List<ProductWithAttributesDTO> dtos =
                products.stream()
                        .map(this.productToDTOMapping::map)
                        .collect(Collectors.toList());

        return new ExportProductsInRangeDTO(dtos);
    }
}