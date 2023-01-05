package com.spawn.hexagonalarq.product.application.ports.input;

import com.spawn.hexagonalarq.product.domain.models.Product;

import java.util.List;

public interface GetProductUseCase {

    Product findProductById(Long id);

    List<Product> findAll();
}
