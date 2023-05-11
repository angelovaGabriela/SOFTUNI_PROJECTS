package com.plannerapp.service;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.service.TaskServiceModel;
import com.plannerapp.model.view.TaskViewModel;

import java.util.List;
import java.util.Set;

public interface TaskService {
    void addTaskToDB(TaskServiceModel taskServiceModel);

    List<TaskViewModel> findAllTasks();

    Integer getTotalTaskNumber();

    Task findTaskById(Long taskID);


}
