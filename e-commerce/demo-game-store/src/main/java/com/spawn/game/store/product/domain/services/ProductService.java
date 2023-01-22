package com.spawn.game.store.product.domain.services;

import com.spawn.game.store.product.application.ports.input.CreateProductUseCase;
import com.spawn.game.store.product.application.ports.output.ProductEventPublisher;
import com.spawn.game.store.product.application.ports.output.ProductOutPort;
import com.spawn.game.store.product.domain.events.ProductCreatedEvent;
import com.spawn.game.store.product.domain.models.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService  implements CreateProductUseCase {

    private final ProductOutPort productOutPort;

    private final ProductEventPublisher productEventPublisher;
    @Override
    public Product createProduct(Product product) {
        product = productOutPort.saveProduct(product);
        productEventPublisher.publishProductCreateEvent(new ProductCreatedEvent(product.getId()));
        return product;
    }
}
