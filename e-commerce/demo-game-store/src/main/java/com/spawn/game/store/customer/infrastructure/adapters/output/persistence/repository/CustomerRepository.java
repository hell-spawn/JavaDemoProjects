package com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository;

import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
}
