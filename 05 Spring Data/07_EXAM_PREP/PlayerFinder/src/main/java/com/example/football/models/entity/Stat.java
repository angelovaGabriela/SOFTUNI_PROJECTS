package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private float shooting;


    @Column(nullable = false)
    private float passing;


    @Column(nullable = false)
    private float endurance;

    public Stat() {}


    public long getId() {
        return id;
    }

    public float getShooting() {
        return shooting;
    }

    public float getPassing() {
        return passing;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }
}
