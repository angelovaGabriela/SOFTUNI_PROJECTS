package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Task;
import com.plannerapp.model.service.TaskServiceModel;
import com.plannerapp.model.view.TaskViewModel;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.PriorityService;
import com.plannerapp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final ModelMapper modelMapper;

    private final PriorityService priorityService;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(ModelMapper modelMapper, PriorityService priorityService, TaskRepository taskRepository) {
        this.modelMapper = modelMapper;
        this.priorityService = priorityService;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(TaskServiceModel taskServiceModel) {
        Task task = modelMapper.map(taskServiceModel, Task.class);

        task.setPriority(priorityService.findByPriorityNameEnum(taskServiceModel.getPriority()));

        taskRepository.save(task);
    }

    @Override
    public List<TaskViewModel> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskViewModel.class))
                .collect(Collectors.toList());
    }
}
