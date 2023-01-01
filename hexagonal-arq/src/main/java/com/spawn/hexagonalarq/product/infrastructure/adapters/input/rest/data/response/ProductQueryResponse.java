package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductQueryResponse {

    private Long id;
    private String name;
    private String description;
}
