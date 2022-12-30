package com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.repository;

import com.spawn.hexagonalarq.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
