package ru.nsu.bayramov.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.bayramov.deliveryservice.model.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
