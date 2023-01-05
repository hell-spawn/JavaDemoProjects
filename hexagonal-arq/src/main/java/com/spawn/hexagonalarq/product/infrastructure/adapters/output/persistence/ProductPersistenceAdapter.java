package com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence;

import com.spawn.hexagonalarq.product.application.ports.output.ProductOutPort;
import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            Product product = productPersistenceMapper.toProduct(productEntity.get());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
       List<ProductEntity> productEntities =  productRepository.findAll();
       if(productEntities.size() > 0){
            return productPersistenceMapper.map(productEntities);
       }
       return new ArrayList<Product>();
    }
}
