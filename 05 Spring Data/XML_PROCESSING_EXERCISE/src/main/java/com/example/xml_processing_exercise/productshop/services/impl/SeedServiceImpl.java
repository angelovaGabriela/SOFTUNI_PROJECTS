package com.example.xml_processing_exercise.productshop.services.impl;



import com.example.xml_processing_exercise.productshop.entities.categoties.Category;
import com.example.xml_processing_exercise.productshop.entities.categoties.CategoriesImportDTO;
import com.example.xml_processing_exercise.productshop.entities.products.Products;
import com.example.xml_processing_exercise.productshop.entities.products.ProductsImportDTO;
import com.example.xml_processing_exercise.productshop.entities.users.User;
import com.example.xml_processing_exercise.productshop.entities.users.UsersImportDTO;
import com.example.xml_processing_exercise.productshop.repositories.CategoryRepository;
import com.example.xml_processing_exercise.productshop.repositories.ProductRepository;
import com.example.xml_processing_exercise.productshop.repositories.UserRepository;
import com.example.xml_processing_exercise.productshop.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {
    private static final String USERS_XML_PATH = "src/main/resources/productshop/users.xml";
    private static final String CATEGORIES_XML_PATH = "src/main/resources/productshop/categories.xml";
    private static final String PRODUCT_XML_PATH = "src/main/resources/productshop/products.xml";


    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    @Autowired
    public SeedServiceImpl(CategoryRepository categoryRepository,
                           UserRepository userRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {
     JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
     Unmarshaller unmarshaller = context.createUnmarshaller();
     FileReader xmlReader = new FileReader(USERS_XML_PATH);

     UsersImportDTO usersDTOs = (UsersImportDTO) unmarshaller.unmarshal(xmlReader);

    //Converting from DTO to User entity

        List<User> entities = usersDTOs.getUsers()
                .stream()
                .map(dto -> this.modelMapper.map(dto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(entities);
    }

    @Override
    public void seedCategories() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(CategoriesImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH);

        // converting data from xml file to DTO object
        CategoriesImportDTO importDTO = (CategoriesImportDTO) unmarshaller.unmarshal(xmlReader);
        List<Category> entities = importDTO.
                getCategories()
                .stream()
                .map(cn -> new Category(cn.getName())).toList();

        this.categoryRepository.saveAll(entities);

        }

    @Override
    public void seedProducts() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader xmlReader = new FileReader(PRODUCT_XML_PATH);
        ProductsImportDTO importDTOs = (ProductsImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Products> entities = importDTOs
                .getProducts()
                .stream()
                .map(dto -> new Products(dto.getName(), dto.getPrice()))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer).toList();

        this.productRepository.saveAll(entities);
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

        // 0...4 + 1 = 1...5
        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        return this.userRepository.findById(randomUserId);
    }
}

