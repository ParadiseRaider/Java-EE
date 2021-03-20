package ru.geek.persist;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
