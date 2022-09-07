package com.example.jsonexe.productshop.services.impl;

import com.example.jsonexe.productshop.entities.Products;
import com.example.jsonexe.productshop.entities.categoties.Category;
import com.example.jsonexe.productshop.entities.categoties.CategoryImportDTO;
import com.example.jsonexe.productshop.entities.products.ProductImportDTO;
import com.example.jsonexe.productshop.entities.users.User;
import com.example.jsonexe.productshop.entities.users.UserImportDTO;
import com.example.jsonexe.productshop.repositories.CategoryRepository;
import com.example.jsonexe.productshop.repositories.ProductRepository;
import com.example.jsonexe.productshop.repositories.UserRepository;
import com.example.jsonexe.productshop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String USERS_JSON_PATH = "src/main/resources/productshop/users.json";
    private static final String CATEGORIES_JSON_PATH = "src/main/resources/productshop/categories.json";
    private static final String PRODUCT_JSON_PATH = "src/main/resources/productshop/products.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        // 1. map JSON to DTO
        // 2. map DTO to User
        FileReader fileReader = new FileReader(USERS_JSON_PATH);
        UserImportDTO[] userImportDTOS = this.gson.fromJson(fileReader, UserImportDTO[].class);

      List<User> users = Arrays.stream(userImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());

      this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH);
        CategoryImportDTO[] categoryImportDTOS = this.gson.fromJson(fileReader, CategoryImportDTO[].class);

        List<Category> categories = Arrays.stream(categoryImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCT_JSON_PATH);
        ProductImportDTO[] productImportDTOS = this.gson.fromJson(fileReader, ProductImportDTO[].class);

        List<Products> products = Arrays.stream(productImportDTOS)
                .map(importDTO -> this.modelMapper.map(importDTO, Products.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .collect(Collectors.toList());

        this.productRepository.saveAll(products);
    }

    private Products setRandomCategories(Products product) {
       Random random = new Random();
        long categoriesDBCount = this.categoryRepository.count();
       int count = random.nextInt((int) categoriesDBCount);

       Set<Category> categories = new HashSet<>();

        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDBCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());

        }

        product.setCategories(categories);
        return product;
    }

    private Products setRandomBuyer(Products product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());

    return product;
    }

    private Products setRandomSeller(Products product) {
        Optional<User> seller = getRandomUser();

        product.setSeller(seller.get());

    return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1; //count = 5 // 1...5

        return this.userRepository.findById(randomUserId);
    }
}

