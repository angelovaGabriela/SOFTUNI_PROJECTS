package com.bonappetit.service.impl;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.service.RecipeServiceModel;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.service.CategoryService;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;

    private final CurrentUser currentUser;

    private final CategoryService categoryService;

    public RecipeServiceImpl(UserService userService, ModelMapper modelMapper, RecipeRepository recipeRepository, CurrentUser currentUser, CategoryService categoryService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.recipeRepository = recipeRepository;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }


    @Override
    public void addRecipe(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = modelMapper.map(recipeServiceModel, Recipe.class);
        User user = this.userService.findById(currentUser.getId());


        recipe.setCategory(this.categoryService.findByName(recipeServiceModel.getCategory()));

        //adding bidirectional relation because the recipe needs to know her creator
        recipe.setAddedBy(user);
        this.recipeRepository.save(recipe);


        //adding the recipe to the offers collection of the seller
        user.recipesAddedByMe(recipe);
        this.userService.saveUser(user);

    }

    @Override
    public Recipe findById(Long recipeId) {

       return this.recipeRepository.findById(recipeId).orElse(null);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        this.recipeRepository.save(recipe);
    }
}
