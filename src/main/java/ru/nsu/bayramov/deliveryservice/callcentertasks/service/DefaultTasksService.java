package ru.nsu.bayramov.deliveryservice.callcentertasks.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.bayramov.deliveryservice.dtotransformservice.DtoTransformService;
import ru.nsu.bayramov.deliveryservice.model.domain.Order;
import ru.nsu.bayramov.deliveryservice.model.domain.Task;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;
import ru.nsu.bayramov.deliveryservice.model.dto.TasksFilterDto;
import ru.nsu.bayramov.deliveryservice.repositories.OrderRepository;
import ru.nsu.bayramov.deliveryservice.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultTasksService implements TasksService {
    private final TaskRepository taskRepository;
    private final OrderRepository orderRepository;

    private final DtoTransformService dtoTransformService;

    @Override
    public Task createTask(int orderId) {
        Order order = orderRepository.
                findById(orderId).
                orElseThrow(() -> new IllegalArgumentException("Order with id " + orderId + " not find"));

        Task task = new Task();

        task.setOrder(order);
        task.setDate(new Date());
        task.setState(false);

        log.info("Task with order id {} created", orderId);

        return taskRepository.save(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            tasks.add(dtoTransformService.convertToTaskDto(task));
        }

        log.info("Task list with size {} transferred", tasks.size());
        return tasks;
    }

    @Override
    public List<TaskDto> getAllTasksWithFilter(TasksFilterDto tasksFilterDto) {
        if (tasksFilterDto.getStartDate() == null || tasksFilterDto.getEndDate() == null) {
            throw new IllegalArgumentException("One of the interval dates is missing");
        } else if (tasksFilterDto.getStartDate().after(tasksFilterDto.getEndDate())) {
            throw new IllegalArgumentException("Incorrect date sequence");
        }

        List<TaskDto> tasks = new ArrayList<>();

        if (tasksFilterDto.getOrderId() != null) {
            Order order = orderRepository.
                    findById(tasksFilterDto.getOrderId()).
                    orElseThrow(() -> new IllegalArgumentException("Order with id " + tasksFilterDto.getOrderId() + " not find"));

            for (Task task : taskRepository.findAllByDateBetweenAndOrder(tasksFilterDto.getStartDate(), tasksFilterDto.getEndDate(), order)) {
                tasks.add(dtoTransformService.convertToTaskDto(task));
            }
        } else {
            for (Task task : taskRepository.findAllByDateBetween(tasksFilterDto.getStartDate(), tasksFilterDto.getEndDate())) {
                tasks.add(dtoTransformService.convertToTaskDto(task));
            }
        }

        log.info("Task list with size {} transferred", tasks.size());
        return tasks;
    }
}
