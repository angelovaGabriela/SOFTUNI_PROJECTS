package com.example.demo.entities;

import com.example.demo.entities.users.User;

import javax.persistence.*;
import java.util.Set;

@Entity (name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // един потребител има много поръчки
    // тези поръчки могат да принадлежат на този потребител
    // те не съществуват без потребителя
    @ManyToOne
    private User buyer;


    // една поръчка може да има мнгого игри
    // една игра може да присъства в много поръчки
    // без cascade - няма логика да купувам игри, които не съществуват и да трия

    @ManyToMany (fetch = FetchType.EAGER) // щом съм стигнала до "поръчки", цялата информация от тук ми трябва = EAGER
    private Set<Game> products;


    public Order () {}
    public Order(User buyer, Set<Game> products) {
        this.buyer = buyer;
        this.products = products;
    }
}
