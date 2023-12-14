package com.bonappetit.web;

import com.bonappetit.model.binding.RecipeAddBindingModel;
import com.bonappetit.model.service.RecipeServiceModel;
import com.bonappetit.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/recipe/add")
    public String addOffer() {
        return "recipe-add";
    }

    @PostMapping("/recipe/add")
    public String confirmOfferAdd(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);

            return "redirect:/recipe/add";
        }

        this.recipeService.addRecipe(modelMapper.map(recipeAddBindingModel, RecipeServiceModel.class));


        return "redirect:/";
    }


    @ModelAttribute
    public RecipeAddBindingModel recipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }

}