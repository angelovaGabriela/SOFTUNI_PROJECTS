package com.plannerapp.model.view;

import com.plannerapp.model.entity.Priority;

import java.time.LocalDate;

public class TaskViewModel {

    private Long id;
    private LocalDate dueDate;
    private Priority priority;
    private String description;

    public TaskViewModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
