package com.example.__LEVANDINH.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}