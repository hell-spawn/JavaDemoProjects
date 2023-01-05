package com.spawn.hexagonalarq.product.application.ports.output;

import com.spawn.hexagonalarq.product.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductOutPort {

    Product saveProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> findAll();
}
