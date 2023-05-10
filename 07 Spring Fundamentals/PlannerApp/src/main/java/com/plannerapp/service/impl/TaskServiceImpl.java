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
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<TaskViewModel> findAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getTotalTaskNumber() {

      return this.findAllTasks().size();
    }

    @Override
    public Task findTaskById(Long taskID) {
        return this.taskRepository.findById(taskID).orElseThrow();
    }

    @Override
    public Set<TaskViewModel> getAssignedTasks(Long userID) {
        return this.taskRepository.findAllByUserId(userID)
                .stream()
                .map(this::mapToTaskViewModel)
                .collect(Collectors.toSet());
    }

    private TaskViewModel mapToTaskViewModel(Task task) {
        TaskViewModel taskView = new TaskViewModel();
        taskView.setId(task.getId());
        taskView.setDueDate(task.getDueDate());
        taskView.setPriority(task.getPriority());
        taskView.setDescription(task.getDescription());
        return taskView;
    }
}
