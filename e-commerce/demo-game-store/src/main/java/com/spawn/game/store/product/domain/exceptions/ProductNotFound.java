package com.spawn.game.store.product.domain.exceptions;

public class ProductNotFound extends  RuntimeException {

    public ProductNotFound(String message) {
        super(message);
    }

}
