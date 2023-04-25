package com.plannerapp.model.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PriorityNameEnum name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany
    private Set<Task> tasks;

    public Priority() {}

    public PriorityNameEnum getName() {
        return name;
    }

    public void setName(PriorityNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
