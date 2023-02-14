package com.spawn.game.store.customer.application.input;

import com.spawn.game.store.customer.domain.models.Customer;
import com.spawn.game.store.customer.domain.services.CustomerService;

public interface CreateCustomerUseCase {

    public Customer createCustomer(Customer customer);
}
