package com.spawn.hexagonalarq.product.domain.exceptions;

public class ProductNotFound extends  RuntimeException{

    public ProductNotFound(String message) {
        super(message);
    }

}
