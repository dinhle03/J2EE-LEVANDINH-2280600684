package com.example.__LEVANDINH.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private Double price;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
