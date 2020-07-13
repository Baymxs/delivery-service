package ru.nsu.bayramov.deliveryservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    private Date deliveryDate;

    private Boolean state;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Product> products;
}
