package ru.nsu.bayramov.deliveryservice.callcentertasks.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nsu.bayramov.deliveryservice.model.domain.Order;
import ru.nsu.bayramov.deliveryservice.model.domain.Task;
import ru.nsu.bayramov.deliveryservice.model.dto.OrderDto;
import ru.nsu.bayramov.deliveryservice.model.dto.TaskDto;
import ru.nsu.bayramov.deliveryservice.model.dto.TasksFilterDto;
import ru.nsu.bayramov.deliveryservice.repositories.OrderRepository;
import ru.nsu.bayramov.deliveryservice.repositories.TaskRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
class DefaultTasksServiceTest {
    @Autowired
    private TasksService tasksService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private OrderRepository orderRepository;

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Test
    void createTask() {
        Order order = new Order();
        order.setId(1);
        order.setName("name");
        order.setAddress("address");
        order.setDeliveryDate(parseDate("2020-07-26"));
        order.setProducts(null);
        order.setState(false);
        order.setUser(null);

        Optional<Order> orderOptional = Optional.of(order);
        Mockito.when(orderRepository.findById(1)).thenReturn(orderOptional);

        tasksService.createTask(1);

        verify(taskRepository, times(1)).save((any(Task.class)));
    }

    @Test
    void getAllTasks() {
        List<Task> testTasks = new ArrayList<>();

        Task testTask1 = new Task();
        testTask1.setId(1);
        testTask1.setState(false);
        testTask1.setDate(new Date(1234));
        testTask1.setOrder(null);

        testTasks.add(testTask1);

        Mockito.when(taskRepository.findAll()).thenReturn(testTasks);

        TaskDto expectedTask = new TaskDto();
        expectedTask.setId(1);
        expectedTask.setState(false);
        expectedTask.setDate(new Date(1234));
        expectedTask.setOrder(null);

        List<TaskDto> tasks = tasksService.getAllTasks();

        TaskDto actualTask = tasks.get(0);

        Assertions.assertEquals(expectedTask.getId(), actualTask.getId());
        Assertions.assertEquals(expectedTask.getState(), actualTask.getState());
        Assertions.assertEquals(expectedTask.getDate(), actualTask.getDate());
        Assertions.assertEquals(expectedTask.getOrder(), actualTask.getOrder());
    }

    @Test
    void getAllTasksWithFilterWithWrongDates() {
        TasksFilterDto wrongFilter = new TasksFilterDto();
        wrongFilter.setStartDate(parseDate("2020-07-28"));
        wrongFilter.setEndDate(parseDate("2020-07-25"));

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tasksService.getAllTasksWithFilter(wrongFilter);
        });

        Assertions.assertEquals("Incorrect date sequence", exception.getMessage());
    }

    @Test
    void getAllTasksWithFilterWittNullStartDate() {
        TasksFilterDto wrongFilter = new TasksFilterDto();
        wrongFilter.setStartDate(parseDate(""));
        wrongFilter.setEndDate(parseDate("2020-07-25"));

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tasksService.getAllTasksWithFilter(wrongFilter);
        });

        Assertions.assertEquals("One of the interval dates is missing", exception.getMessage());
    }

    @Test
    void getAllTasksWithFilterWittNullEndDate() {
        TasksFilterDto wrongFilter = new TasksFilterDto();
        wrongFilter.setStartDate(parseDate("2020-07-28"));
        wrongFilter.setEndDate(parseDate(""));

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tasksService.getAllTasksWithFilter(wrongFilter);
        });

        Assertions.assertEquals("One of the interval dates is missing", exception.getMessage());
    }

    @Test
    void getAllTasksWithFilterWithDates() {
        TasksFilterDto filter = new TasksFilterDto();
        filter.setStartDate(parseDate("2020-07-10"));
        filter.setEndDate(parseDate("2020-07-20"));

        List<Task> testTasks = new ArrayList<>();

        Task testTask1 = new Task();
        testTask1.setId(1);
        testTask1.setState(false);
        testTask1.setDate(parseDate("2020-07-15"));
        testTask1.setOrder(null);

        Task testTask2 = new Task();
        testTask2.setId(2);
        testTask2.setState(false);
        testTask2.setDate(parseDate("2020-07-19"));
        testTask2.setOrder(null);

        testTasks.add(testTask1);
        testTasks.add(testTask2);

        Mockito.when(taskRepository.findAllByDateBetween(parseDate("2020-07-10"), parseDate("2020-07-20"))).thenReturn(testTasks);

        TaskDto expectedTask1 = new TaskDto();
        expectedTask1.setId(1);
        expectedTask1.setState(false);
        expectedTask1.setDate(parseDate("2020-07-15"));
        expectedTask1.setOrder(null);

        TaskDto expectedTask2 = new TaskDto();
        expectedTask2.setId(2);
        expectedTask2.setState(false);
        expectedTask2.setDate(parseDate("2020-07-19"));
        expectedTask2.setOrder(null);

        List<TaskDto> tasks = tasksService.getAllTasksWithFilter(filter);

        TaskDto actualTask1 = tasks.get(0);
        TaskDto actualTask2 = tasks.get(1);

        Assertions.assertEquals(expectedTask1.getId(), actualTask1.getId());
        Assertions.assertEquals(expectedTask1.getState(), actualTask1.getState());
        Assertions.assertEquals(expectedTask1.getDate(), actualTask1.getDate());
        Assertions.assertEquals(expectedTask1.getOrder(), actualTask1.getOrder());

        Assertions.assertEquals(expectedTask2.getId(), actualTask2.getId());
        Assertions.assertEquals(expectedTask2.getState(), actualTask2.getState());
        Assertions.assertEquals(expectedTask2.getDate(), actualTask2.getDate());
        Assertions.assertEquals(expectedTask2.getOrder(), actualTask1.getOrder());
    }

    @Test
    void getAllTasksWithFilterWithDatesAndOrder() {
        TasksFilterDto filter = new TasksFilterDto();
        filter.setStartDate(parseDate("2020-07-10"));
        filter.setEndDate(parseDate("2020-07-20"));
        filter.setOrderId(1);

        List<Task> testTasks = new ArrayList<>();

        Order order = new Order();
        order.setId(1);
        order.setName("name");
        order.setAddress("address");
        order.setDeliveryDate(parseDate("2020-07-26"));
        order.setProducts(null);
        order.setState(false);
        order.setUser(null);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(1);
        orderDto.setName("name");
        orderDto.setAddress("address");
        orderDto.setDeliveryDate(parseDate("2020-07-26"));
        orderDto.setProducts(null);
        orderDto.setState(false);
        orderDto.setUser(null);

        Task testTask1 = new Task();
        testTask1.setId(1);
        testTask1.setState(false);
        testTask1.setDate(parseDate("2020-07-15"));
        testTask1.setOrder(order);

        Task testTask2 = new Task();
        testTask2.setId(2);
        testTask2.setState(false);
        testTask2.setDate(parseDate("2020-07-19"));
        testTask2.setOrder(order);

        testTasks.add(testTask1);
        testTasks.add(testTask2);

        Optional<Order> orderOptional = Optional.of(order);
        Mockito.when(orderRepository.findById(1)).thenReturn(orderOptional);

        Mockito.when(taskRepository.findAllByDateBetweenAndOrder(parseDate("2020-07-10"), parseDate("2020-07-20"), order)).thenReturn(testTasks);

        TaskDto expectedTask1 = new TaskDto();
        expectedTask1.setId(1);
        expectedTask1.setState(false);
        expectedTask1.setDate(parseDate("2020-07-15"));
        expectedTask1.setOrder(orderDto);

        TaskDto expectedTask2 = new TaskDto();
        expectedTask2.setId(2);
        expectedTask2.setState(false);
        expectedTask2.setDate(parseDate("2020-07-19"));
        expectedTask2.setOrder(orderDto);

        List<TaskDto> tasks = tasksService.getAllTasksWithFilter(filter);

        TaskDto actualTask1 = tasks.get(0);
        TaskDto actualTask2 = tasks.get(1);

        Assertions.assertEquals(expectedTask1.getId(), actualTask1.getId());
        Assertions.assertEquals(expectedTask1.getState(), actualTask1.getState());
        Assertions.assertEquals(expectedTask1.getDate(), actualTask1.getDate());

        Assertions.assertEquals(expectedTask1.getOrder().getId(), actualTask1.getOrder().getId());
        Assertions.assertEquals(expectedTask1.getOrder().getName(), actualTask1.getOrder().getName());
        Assertions.assertEquals(expectedTask1.getOrder().getAddress(), actualTask1.getOrder().getAddress());
        Assertions.assertEquals(expectedTask1.getOrder().getDeliveryDate(), actualTask1.getOrder().getDeliveryDate());
        Assertions.assertEquals(expectedTask1.getOrder().getProducts(), actualTask1.getOrder().getProducts());
        Assertions.assertEquals(expectedTask1.getOrder().getState(), actualTask1.getOrder().getState());
        Assertions.assertEquals(expectedTask1.getOrder().getUser(), actualTask1.getOrder().getUser());

        Assertions.assertEquals(expectedTask2.getId(), actualTask2.getId());
        Assertions.assertEquals(expectedTask2.getState(), actualTask2.getState());
        Assertions.assertEquals(expectedTask2.getDate(), actualTask2.getDate());

        Assertions.assertEquals(expectedTask2.getOrder().getId(), actualTask2.getOrder().getId());
        Assertions.assertEquals(expectedTask2.getOrder().getName(), actualTask2.getOrder().getName());
        Assertions.assertEquals(expectedTask2.getOrder().getAddress(), actualTask2.getOrder().getAddress());
        Assertions.assertEquals(expectedTask2.getOrder().getDeliveryDate(), actualTask2.getOrder().getDeliveryDate());
        Assertions.assertEquals(expectedTask2.getOrder().getProducts(), actualTask2.getOrder().getProducts());
        Assertions.assertEquals(expectedTask2.getOrder().getState(), actualTask2.getOrder().getState());
        Assertions.assertEquals(expectedTask2.getOrder().getUser(), actualTask2.getOrder().getUser());
    }
}