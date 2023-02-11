package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateResponse {

    private String id;
    private String name;
    private String reference;
    private String description;
    private String details;
    private BigDecimal price;

    private ProductTypeResponse productType;
}