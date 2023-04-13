package com.plannerapp.service;

import com.plannerapp.model.service.TaskServiceModel;
import com.plannerapp.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(TaskServiceModel taskServiceModel);

    List<TaskViewModel> findAllTasks();
}
