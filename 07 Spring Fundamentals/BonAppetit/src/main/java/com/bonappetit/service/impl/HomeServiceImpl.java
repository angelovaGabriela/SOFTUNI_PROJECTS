package com.bonappetit.service.impl;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.service.HomeService;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    private final RecipeService recipeService;
    private final UserService userService;

    public HomeServiceImpl(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @Override
    public void addToFavourites(Long recipeId, Long userId) {

        // search the recipe
        Recipe recipe = this.recipeService.findById(recipeId);
        // search the user
        User user = this.userService.findById(userId);
        // if the recipe is not in favourites ADD IT, if it is RETURN
        if (user.getFavouriteRecipes().stream().noneMatch(r -> r.getId().equals(recipe.getId()))) {
            user.getFavouriteRecipes().add(recipe);
            recipe.setAddedBy(user);
        } else {
            return;
        }

        userService.saveUser(user);
        recipeService.saveRecipe(recipe);

    }
}
