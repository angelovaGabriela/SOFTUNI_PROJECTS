package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.Product;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.repository.ProductRepository;
import com.example.shoppingList.securityUtils.CurrentUser;
import com.example.shoppingList.service.CategoryService;
import com.example.shoppingList.service.ProductService;
import com.example.shoppingList.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService,
                              ModelMapper modelMapper,
                              CurrentUser currentUser,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);

        product.setCategory(categoryService.findByCategoryNameEnum(productServiceModel.getCategory()));

        productRepository.save(product);

    }


}
