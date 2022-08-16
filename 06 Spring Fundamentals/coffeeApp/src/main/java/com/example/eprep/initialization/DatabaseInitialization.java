package com.example.eprep.initialization;

import com.example.eprep.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitialization implements CommandLineRunner {

    private final CategoryService categoryService;

    public DatabaseInitialization(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {

        categoryService.initCategories();

    }
}
