package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeRequest {
    @NotNull(message = "ProductType Id cannot be empty")
    private Long id;
    private String description;
}
