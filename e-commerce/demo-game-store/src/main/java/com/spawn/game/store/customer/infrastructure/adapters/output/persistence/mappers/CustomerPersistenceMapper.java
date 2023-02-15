package com.spawn.game.store.customer.infrastructure.adapters.output.persistence.mappers;

import com.spawn.game.store.customer.domain.models.Customer;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);
}
