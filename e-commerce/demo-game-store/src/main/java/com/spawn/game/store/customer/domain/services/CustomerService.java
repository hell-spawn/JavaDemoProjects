package com.spawn.game.store.customer.domain.services;

import com.spawn.game.store.customer.application.ports.input.CreateCustomerUseCase;
import com.spawn.game.store.customer.application.ports.output.CustomerOutPort;
import com.spawn.game.store.customer.domain.models.Customer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerService implements CreateCustomerUseCase {

    private final CustomerOutPort customerOutPort;
    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerSaved = customerOutPort.saveCustomer(customer);
        return customerSaved;
    }

}
