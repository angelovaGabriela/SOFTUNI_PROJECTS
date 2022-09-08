package com.example.jsonexe.productshop.entities.users;

import com.example.jsonexe.productshop.entities.products.SoldProductsDTO;

import java.util.List;

public class UserWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductsDTO> itemsBought;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductsDTO> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<SoldProductsDTO> itemsBought) {
        this.itemsBought = itemsBought;
    }
}
