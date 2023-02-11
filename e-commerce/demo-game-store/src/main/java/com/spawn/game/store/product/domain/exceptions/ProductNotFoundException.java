package com.spawn.game.store.product.domain.exceptions;

public class ProductNotFoundException extends  RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}
