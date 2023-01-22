package com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository;

import com.spawn.game.store.product.infrastructure.adapters.output.persistence.repository.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
