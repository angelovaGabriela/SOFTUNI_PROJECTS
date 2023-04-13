package com.plannerapp.model.binding;

import com.plannerapp.model.entity.PriorityNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TaskAddBindingModel {


    @NotEmpty
    private String description;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //"yyyy-MM-dd"
    private LocalDate dueDate;

    @NotNull
    private PriorityNameEnum priority;

    public TaskAddBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityNameEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityNameEnum priority) {
        this.priority = priority;
    }
}
