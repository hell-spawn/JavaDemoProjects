package com.spawn.hexagonalarq.product.application.ports.input;

import com.spawn.hexagonalarq.product.domain.models.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);
}
