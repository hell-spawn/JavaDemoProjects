package com.spawn.game.store.product.infrastructure.adapters.output.persistence.mappers;

import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper  {

    ProductEntity toProductEntity(Product product);
    Product toProduct(ProductEntity productEntity);
}
