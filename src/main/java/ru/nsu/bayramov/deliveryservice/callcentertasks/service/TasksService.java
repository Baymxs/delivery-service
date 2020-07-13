package ru.nsu.bayramov.deliveryservice.callcentertasks.service;

import ru.nsu.bayramov.deliveryservice.model.domain.Task;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;
import ru.nsu.bayramov.deliveryservice.model.dto.TasksFilterDto;

import java.util.List;

public interface TasksService {
    Task createTask(int orderId);

    List<TaskDto> getAllTasks();

    List<TaskDto> getAllTasksWithFilter(TasksFilterDto tasksFilterDto);
}
