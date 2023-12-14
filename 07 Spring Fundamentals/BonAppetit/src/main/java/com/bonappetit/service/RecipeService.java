package com.bonappetit.service;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.service.RecipeServiceModel;

public interface RecipeService {
    void addRecipe(RecipeServiceModel recipeServiceModel);

    Recipe findById(Long recipeId);

    void saveRecipe(Recipe recipe);
}
