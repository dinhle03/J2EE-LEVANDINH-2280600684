package com.example.__LEVANDINH.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private String image;
    private Double price;
    private Integer quantity;
}
