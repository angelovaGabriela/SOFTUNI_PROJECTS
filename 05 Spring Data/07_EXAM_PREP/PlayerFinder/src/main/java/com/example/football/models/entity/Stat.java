package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @Positive
    @Column(nullable = false)
    private double shooting;

    // @Positive
    @Column(nullable = false)
    private double passing;

    // @Positive
    @Column(nullable = false)
    private double endurance;

    public Stat() {}


}
