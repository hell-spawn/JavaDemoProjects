package com.spawn.game.store.customer.infrastructure.adapters.output.persistence;

import com.spawn.game.store.customer.application.ports.output.CustomerOutPort;
import com.spawn.game.store.customer.domain.models.Customer;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.mappers.CustomerPersistenceMapper;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.CustomerRepository;
import com.spawn.game.store.customer.infrastructure.adapters.output.persistence.repository.entities.CustomerEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerPersistenceAdapter implements CustomerOutPort {

    private final CustomerRepository customerRepository;

    private final CustomerPersistenceMapper customerPersistenceMapper;
    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerPersistenceMapper.toCustomerEntity(customer);
        customerEntity = customerRepository.save(customerEntity);
        return customerPersistenceMapper.toCustomer(customerEntity);
    }
}
