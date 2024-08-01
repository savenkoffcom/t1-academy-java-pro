package com.savenkoff.study.task6.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String accNum;

    @Column(nullable = false)
    @Setter
    private Float balance;

    @Column(nullable = false)
    private TypeProduct typeProduct;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
}