package com.bonappetit.service;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initCategory();

    Category findByName(CategoryNameEnum category);
}
