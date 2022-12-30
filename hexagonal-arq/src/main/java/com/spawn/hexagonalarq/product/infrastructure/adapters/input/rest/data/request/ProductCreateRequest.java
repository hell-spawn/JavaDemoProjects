package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductCreateRequest {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    @NotEmpty(message = "Description may not be empty")
    private String description;
}
