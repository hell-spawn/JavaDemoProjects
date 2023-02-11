package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductSearchResponse {

    private String id;
    private String name;
    private String reference;
    private String description;
    private String details;
    private BigDecimal price;

}
