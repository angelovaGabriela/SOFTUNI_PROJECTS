package com.example.shoppingList.service;

import com.example.shoppingList.model.entity.Category;
import com.example.shoppingList.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum category);
}
