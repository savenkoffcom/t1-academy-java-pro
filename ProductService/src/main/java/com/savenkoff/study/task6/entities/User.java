package com.savenkoff.study.task6.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "User.withInProducts",
        attributeNodes = {
                @NamedAttributeNode("products")
        }
)

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Product> products = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }
}
