package com.spawn.game.store.product.domain.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String id;
    private ProductType productType;
    private String name;
    private BigDecimal price;
    private String reference;
    private String description;
    private String details;
}
