package com.bonappetit.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne
    private User addedBy;

    public Recipe() {}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return getName().equals(recipe.getName()) && getCategory().equals(recipe.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCategory());
    }
}
