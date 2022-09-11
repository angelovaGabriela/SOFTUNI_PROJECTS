package com.example.xml_processing_exercise.productshop.services.impl;



import com.example.xml_processing_exercise.productshop.entities.categoties.Category;
import com.example.xml_processing_exercise.productshop.entities.categoties.CategoryImportDTO;
import com.example.xml_processing_exercise.productshop.repositories.CategoryRepository;
import com.example.xml_processing_exercise.productshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {
    private static final String USERS_XML_PATH = "src/main/resources/productshop/users.xml";
    private static final String CATEGORIES_XML_PATH = "src/main/resources/productshop/categories.xml";
    private static final String PRODUCT_XML_PATH = "src/main/resources/productshop/products.xml";


    private final CategoryRepository categoryRepository;

    @Autowired
    public SeedServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedUsers() throws JAXBException, FileNotFoundException {

    }

    @Override
    public void seedCategories() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH);

        // converting data from xml file to DTO object
        CategoryImportDTO importDTO = (CategoryImportDTO) unmarshaller.unmarshal(xmlReader);
        List<Category> entities = importDTO.
                getCategories()
                .stream()
                .map(cn -> new Category(cn.getName())).toList();
//
        this.categoryRepository.saveAll(entities);

        }

    @Override
    public void seedProducts() {

    }

 /*   private Products setRandomCategories(Products product) {
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
    }*/

  /*  private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) usersCount) + 1; //count = 5 // 1...5

        return this.userRepository.findById(randomUserId);
    }*/
}

