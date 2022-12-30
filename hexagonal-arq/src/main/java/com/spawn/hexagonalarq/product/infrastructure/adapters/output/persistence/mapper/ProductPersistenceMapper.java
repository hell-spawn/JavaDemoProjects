package com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.mapper;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
