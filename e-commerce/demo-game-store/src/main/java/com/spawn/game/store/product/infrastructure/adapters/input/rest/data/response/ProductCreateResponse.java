package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCreateResponse {

    private Long id;
    private String name;
    private String description;
}
