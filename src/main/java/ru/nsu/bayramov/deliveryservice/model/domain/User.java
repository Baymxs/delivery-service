package ru.nsu.bayramov.deliveryservice.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
}
