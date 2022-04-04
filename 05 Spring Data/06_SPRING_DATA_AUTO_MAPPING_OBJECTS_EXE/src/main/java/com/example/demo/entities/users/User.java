package com.example.demo.entities.users;

import com.example.demo.entities.Game;
import com.example.demo.entities.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private boolean admin;

    @ManyToMany // cascade & fetch
    // без cascade - няма default value, не ми трябва, когато изтрия потребител играта не трябва да се трие, въпреки, че са свързани
    // fetch - default value - LAZY, е преточвам информацията от базата докато изрично не е казано, че тя ни трябва.
    // един потребител може да притежава много видове игри
    // еднин тип игра може да бъде купена от много хора
    private Set<Game> games;
    // SET (unique elements) because I can't buy one game two times

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer") // искам тази релация да съответства на релацията, която има в ORDER
    private Set<Order> orders;
    // един потребител има много поръчки

    public User() {
        // инициализирам си двете колекции
        // за да се знае, че имам валидни колекции
        this.games = new HashSet<>();
        this.orders = new HashSet<>();

    } // empty ctr

    public User(String email, String password, String fullName) {
        this(); // call the 'empty ctr'
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

