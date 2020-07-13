package ru.nsu.bayramov.deliveryservice.callcentertasks.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.bayramov.deliveryservice.callcentertasks.service.DefaultTasksService;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;
import ru.nsu.bayramov.deliveryservice.model.dto.TasksFilterDto;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/call-center-tasks")
@RequiredArgsConstructor
@Slf4j
public class TasksController {
    private final DefaultTasksService defaultTasksService;

    @PostMapping("/create-task/{order-id}")
    @ApiOperation("Добавление нового задания для call-центра")
    public void createTask(
            @PathVariable("order-id")
            @ApiParam(required = true, value = "Id груза, который не удалось доставить")
                    int orderId) {
        defaultTasksService.createTask(orderId);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Получение списка заданий для прозвона клиентов", response = TaskDto.class, responseContainer = "Set")
    public List<TaskDto> getAllTasks() {
        return defaultTasksService.getAllTasks();
    }

    @GetMapping("/all-filtered")
    @ApiOperation(value = "Получение списка заданий для прозвона клиентов с применением фильтров", response = TaskDto.class, responseContainer = "Set")
    public List<TaskDto> getAllTasksWithFilter(
            @RequestBody TasksFilterDto tasksFilterDto) {
        return defaultTasksService.getAllTasksWithFilter(tasksFilterDto);
    }
}
