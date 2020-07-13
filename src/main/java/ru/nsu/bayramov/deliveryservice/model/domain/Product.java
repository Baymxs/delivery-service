package ru.nsu.bayramov.deliveryservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Integer cost;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders;
}
