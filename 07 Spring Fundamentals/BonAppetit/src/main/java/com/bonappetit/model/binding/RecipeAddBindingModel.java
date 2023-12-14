package com.bonappetit.model.binding;

import com.bonappetit.model.entity.CategoryNameEnum;
import com.bonappetit.model.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RecipeAddBindingModel {

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @Size(min = 2, max = 150)
    private String ingredients;

    @NotNull
    private CategoryNameEnum category;

    private User addedBy;

    public RecipeAddBindingModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
