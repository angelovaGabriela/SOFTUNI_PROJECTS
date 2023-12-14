package com.bonappetit.service;

import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.service.UserServiceModel;
import com.bonappetit.model.view.RecipeViewModel;

import java.util.List;
import java.util.Set;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    void saveUser(User user);
     List<RecipeViewModel> getDesserts(Long id);

    List<RecipeViewModel> getMainDishes(Long id);

    List<RecipeViewModel> getCocktails(Long id);

    Integer getDessertsCount(Long id);

    Integer getCocktailsCount(Long id);
    Integer getMainDishesCount(Long id);

    Set<Recipe> getFavourites(Long id);

    Integer getFavouritesCount(Long id);
}


