package com.spawn.game.store.customer.application.ports.output;

import com.spawn.game.store.customer.domain.models.Customer;

public interface CustomerOutPort {

    Customer saveCustomer(Customer customer);

}
