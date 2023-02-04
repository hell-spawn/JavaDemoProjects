package com.spawn.game.store.product.infrastructure.adapters.output.persistence;

import com.spawn.game.store.product.application.ports.output.ProductOutPort;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutPort {


    private final ProductRepository productRepository;

    private final ProductPersistenceMapper productPersistenceMapper;
    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productPersistenceMapper.toProductEntity(product);
        productEntity = productRepository.save(productEntity);
        return productPersistenceMapper.toProduct(productEntity);
    }

    @Override
    public Optional<Product> searchProductById(String productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if(!productEntity.isPresent()){
          return Optional.empty();
        }
        Product product = productPersistenceMapper.toProduct(productEntity.get());
        return Optional.of(product);
    }
}
