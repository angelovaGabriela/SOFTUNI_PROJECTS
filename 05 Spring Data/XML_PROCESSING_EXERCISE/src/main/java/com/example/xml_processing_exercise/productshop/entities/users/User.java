package com.example.xml_processing_exercise.productshop.entities.users;


import com.example.xml_processing_exercise.productshop.entities.products.Products;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    //REMINDER: initializing that this is the same relation as the one in Products entity
    @OneToMany(targetEntity = Products.class, mappedBy = "seller")
    private List<Products> sellingItems;

    @OneToMany(targetEntity = Products.class, mappedBy = "buyer")
    private List<Products> itemsBought;

    @ManyToMany
    private Set<User> friends; //REMINDER: Set collection because I can't have the same friend in my collection twice

    public User() {
        this.sellingItems = new ArrayList<>();
        this.itemsBought = new ArrayList<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, Integer age) {
        this();

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Products> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<Products> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public List<Products> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<Products> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
