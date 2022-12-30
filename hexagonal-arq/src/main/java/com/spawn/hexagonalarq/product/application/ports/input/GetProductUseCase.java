package com.spawn.hexagonalarq.product.application.ports.input;

import com.spawn.hexagonalarq.product.domain.models.Product;

public interface GetProductUseCase {

    Product findProductById(Long id);
}
