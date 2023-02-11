package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeResponse {

    private Long id;
    private String description;
}
