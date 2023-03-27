package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.Category;
import com.example.shoppingList.model.entity.NameEnum;
import com.example.shoppingList.repository.CategoryRepository;
import com.example.shoppingList.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
      if (this.categoryRepository.count() == 0) {

          Arrays.stream(NameEnum.values())
                  .forEach(categoryName -> {
                      Category category = new Category();
                      category.setName(categoryName);
                      this.categoryRepository.save(category);
                  });


          //   for (NameEnum categoryName : NameEnum.values()) {
          //              Category category = new Category();
          //              category.setName(categoryName);
          //          }
      }
    }
}
