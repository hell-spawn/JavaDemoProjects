package com.spawn.game.store.product.application.ports.output;

import com.spawn.game.store.product.domain.models.Product;

import java.util.Optional;

public interface ProductOutPort {

    public abstract Product saveProduct(Product product);

    public abstract Optional<Product> searchProductById(String productId);
}
