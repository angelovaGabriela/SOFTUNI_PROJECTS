package com.bonappetit.service.impl;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryNameEnum;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategory() {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum ->  {
                    Category category = new Category();
                    category.setName(categoryNameEnum);


                    switch (categoryNameEnum) {
                        case MAIN_DISH-> category.setDescription( "Heart of the meal, substantial and satisfying; main dish delights taste buds.");
                        case DESSERT -> category.setDescription("Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.");
                        case COCKTAIL -> category.setDescription("Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.");
                    }

                    this.categoryRepository.save(category);
                });
    }

    @Override
    public Category findByName(CategoryNameEnum category) {
      return this.categoryRepository.findByName(category);
    }
}
