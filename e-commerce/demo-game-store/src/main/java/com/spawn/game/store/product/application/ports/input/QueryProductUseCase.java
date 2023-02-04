package com.spawn.game.store.product.application.ports.input;

import com.spawn.game.store.product.domain.models.Product;

public interface QueryProductUseCase {

    Product searchProductById(String productId);
}
