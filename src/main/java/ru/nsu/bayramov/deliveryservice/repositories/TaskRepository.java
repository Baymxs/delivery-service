package ru.nsu.bayramov.deliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.bayramov.deliveryservice.model.domain.Order;
import ru.nsu.bayramov.deliveryservice.model.domain.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByDateBetween(Date startDate, Date endDate);

    List<Task> findAllByDateBetweenAndOrder(Date startDate, Date endDate, Order order);
}
