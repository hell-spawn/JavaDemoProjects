package com.spawn.hexagonalarq.product.domain.services;

import com.spawn.hexagonalarq.product.application.ports.input.CreateProductUseCase;
import com.spawn.hexagonalarq.product.application.ports.input.GetProductUseCase;
import com.spawn.hexagonalarq.product.application.ports.output.ProductEventPublisher;
import com.spawn.hexagonalarq.product.application.ports.output.ProductOutPort;
import com.spawn.hexagonalarq.product.domain.events.ProductCreatedEvent;
import com.spawn.hexagonalarq.product.domain.exceptions.ProductNotFound;
import com.spawn.hexagonalarq.product.domain.models.Product;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductOutPort productOutPort;
    private final ProductEventPublisher productEventPublisher;

    @Override
    public Product createProduct(Product product) {
        product = productOutPort.saveProduct(product);
        productEventPublisher.publishProductCreateEvent(new ProductCreatedEvent(product.getId()));
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        return productOutPort.getProductById(id).orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
    }

    @Override
    public List<Product> findAll() {
        return productOutPort.findAll();
    }


}
