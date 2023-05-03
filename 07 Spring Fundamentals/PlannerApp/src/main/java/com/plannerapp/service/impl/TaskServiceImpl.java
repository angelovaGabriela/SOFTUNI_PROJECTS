package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.service.TaskServiceModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.PriorityService;
import com.plannerapp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityService priorityService;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityService priorityService, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.priorityService = priorityService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addTaskToDB(TaskServiceModel taskServiceModel) {

        Task task = modelMapper.map(taskServiceModel, Task.class);
        task.setPriority(priorityService.findByPriorityNameEnum(taskServiceModel.getPriority()));
        taskRepository.save(task);
    }
}
