package com.bonappetit.web;

import com.bonappetit.service.HomeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final UserService userService;
    private final HomeService homeService;
    private final CurrentUser currentUser;

    public HomeController(UserService userService, HomeService homeService, CurrentUser currentUser) {
        this.userService = userService;
        this.homeService = homeService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("desserts", userService.getDesserts(currentUser.getId()));
        model.addAttribute("mainDishes", userService.getMainDishes(currentUser.getId()));
        model.addAttribute("cocktails", userService.getCocktails(currentUser.getId()));


        model.addAttribute("dessertsCount", userService.getDessertsCount(currentUser.getId()));
        model.addAttribute("cocktailsCount", userService.getCocktailsCount(currentUser.getId()));
        model.addAttribute("mainDishesCount", userService.getMainDishesCount(currentUser.getId()));

        model.addAttribute("favourites", userService.getFavourites(currentUser.getId()));
        model.addAttribute("favouritesCount", userService.getFavouritesCount(currentUser.getId()));



        return "home";
    }


    @GetMapping("/recipe/add-to-favourite/{id}")
    public String buyOffer(@PathVariable("id") Long recipeId) {

        if (currentUser.getId() == null) {
            return "redirect:/login";
        }

        this.homeService.addToFavourites(recipeId, currentUser.getId());

        return "redirect:/";
    }
}
