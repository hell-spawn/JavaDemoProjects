package com.spawn.game.store.product.application.ports.output;

import com.spawn.game.store.product.domain.models.Product;

public interface ProductOutPort {

    public abstract Product saveProduct(Product product);
}
