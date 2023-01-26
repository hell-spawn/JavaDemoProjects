package com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request;

import com.spawn.game.store.product.domain.models.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductCreateRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Reference cannot may be empty")
    private String reference;

    @NotEmpty(message = "Details cannot be empty")
    private String details;

    @NotNull(message = "Price cannot be empty")
    private BigDecimal price;
}
