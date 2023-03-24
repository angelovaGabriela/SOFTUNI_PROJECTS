package com.example.shoppingList.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private NameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {}

    public NameEnum name() {
        return name;
    }

    public Category setName(NameEnum name) {
        this.name = name;
        return this;
    }

    public String description() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
