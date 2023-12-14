package com.bonappetit.service.impl;

import com.bonappetit.model.entity.CategoryNameEnum;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.service.UserServiceModel;
import com.bonappetit.model.view.RecipeViewModel;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public User findById(Long id) {
       return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<RecipeViewModel> getDesserts(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        CategoryNameEnum category = CategoryNameEnum.valueOf("DESSERT");

        return getCollection(user, category);
    }

    @Override
    public List<RecipeViewModel> getMainDishes(Long id) {

        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        CategoryNameEnum category = CategoryNameEnum.valueOf("MAIN_DISH");

        return getCollection(user, category);
    }

    @Override
    public List<RecipeViewModel> getCocktails(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        CategoryNameEnum category = CategoryNameEnum.valueOf("COCKTAIL");

        return getCollection(user, category);
    }

    @Override
    public Integer getDessertsCount(Long id) {
       return this.getDesserts(id).size();
    }

    @Override
    public Integer getCocktailsCount(Long id) {
        return this.getCocktails(id).size();
    }

    @Override
    public Integer getMainDishesCount(Long id) {
        return this.getMainDishes(id).size();
    }

    @Override
    public Set<Recipe> getFavourites(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        return user.getFavouriteRecipes();
    }

    @Override
    public Integer getFavouritesCount(Long id) {
        return this.getFavourites(id).size();
    }

    private List<RecipeViewModel> getCollection(User user, CategoryNameEnum category) {
        return user.getAddedRecipes()
                .stream().map(recipe -> modelMapper.map(recipe, RecipeViewModel.class))
                .filter(r -> r.getCategory().getName().equals(category))
                .collect(Collectors.toList());
    }
}
